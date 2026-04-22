
#include <jni.h>
#include <shadowhook.h>
#include "Helper.h"

extern "C" void MSHookFunction(void *symbol, void *replace, void **result) {
    shadowhook_hook_sym_addr(symbol, replace, result);
}

ScopeUtfString::ScopeUtfString(jstring j_str) {
    _j_str = j_str;
    _c_str = getEnv()->GetStringUTFChars(j_str, NULL);
}

ScopeUtfString::~ScopeUtfString() {
    getEnv()->ReleaseStringUTFChars(_j_str, _c_str);
}
