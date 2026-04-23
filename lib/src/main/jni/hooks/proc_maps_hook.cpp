#include <jni.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/syscall.h>
#include "shadowhook.h"
#include <android/log.h>

#define LOG_TAG "VA_AntiDetect"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

// Function pointers for original functions
static int (*orig_open)(const char *pathname, int flags, mode_t mode);
static int (*orig_openat)(int dirfd, const char *pathname, int flags, mode_t mode);

// Filter keywords that expose VirtualApp
static const char* FILTER_KEYWORDS[] = {
    "virtual", "lody", "sandhook", "libva", "v++", "gandaplus"
};
static const int FILTER_COUNT = 6;

// Create a filtered copy of /proc/self/maps and return its fd
static int get_filtered_maps_fd() {
    int real_fd = orig_open("/proc/self/maps", O_RDONLY, 0);
    if (real_fd < 0) return real_fd;

    char tmp_path[256];
    snprintf(tmp_path, sizeof(tmp_path), "/data/user/0/com.xdja.zs/cache/maps_%d", getpid());
    int tmp_fd = orig_open(tmp_path, O_RDWR | O_CREAT | O_TRUNC, 0600);
    if (tmp_fd < 0) {
        orig_open(tmp_path, O_RDWR | O_CREAT | O_TRUNC, 0600);
        close(real_fd);
        // Fallback to real if temp fails
        return orig_open("/proc/self/maps", O_RDONLY, 0);
    }

    FILE* fp_in = fdopen(real_fd, "r");
    FILE* fp_out = fdopen(tmp_fd, "w");
    if (!fp_in || !fp_out) {
        if (fp_in) fclose(fp_in); else close(real_fd);
        if (fp_out) fclose(fp_out); else close(tmp_fd);
        return orig_open("/proc/self/maps", O_RDONLY, 0);
    }

    char line[1024];
    while (fgets(line, sizeof(line), fp_in) != NULL) {
        int drop = 0;
        for (int i = 0; i < FILTER_COUNT; i++) {
            if (strstr(line, FILTER_KEYWORDS[i]) != NULL) {
                drop = 1;
                break;
            }
        }
        if (!drop) {
            fputs(line, fp_out);
        }
    }

    fclose(fp_in);
    fflush(fp_out);
    
    // Rewind out file for reading by the app
    int out_fd = fileno(fp_out);
    lseek(out_fd, 0, SEEK_SET);
    
    // We cannot fclose fp_out here because it would close the fd we want to return.
    // The app is expected to close the fd.
    // Note: fdopen does not dup the fd, so we must just return it and not fclose it.
    // Instead we'll dup it, close the FILE*, and return the dup.
    int ret_fd = dup(out_fd);
    fclose(fp_out);
    
    // Delete temp file (Linux allows reading from unlinked files)
    unlink(tmp_path);
    
    lseek(ret_fd, 0, SEEK_SET);
    return ret_fd;
}

static int proxy_open(const char *pathname, int flags, mode_t mode) {
    if (pathname && strcmp(pathname, "/proc/self/maps") == 0) {
        LOGI("Intercepted open() for /proc/self/maps");
        return get_filtered_maps_fd();
    }
    return orig_open(pathname, flags, mode);
}

static int proxy_openat(int dirfd, const char *pathname, int flags, mode_t mode) {
    if (pathname && strcmp(pathname, "/proc/self/maps") == 0) {
        LOGI("Intercepted openat() for /proc/self/maps");
        return get_filtered_maps_fd();
    }
    return orig_openat(dirfd, pathname, flags, mode);
}

extern "C" JNIEXPORT void JNICALL
Java_com_lody_virtual_helper_AntiDetect_initProcMapsHook(JNIEnv *env, jclass clazz) {
    LOGI("Initializing AntiDetect proc-maps hook...");
    
    shadowhook_hook_sym_name("libc.so", "open", (void *) proxy_open, (void **) &orig_open);
    shadowhook_hook_sym_name("libc.so", "openat", (void *) proxy_openat, (void **) &orig_openat);
}
