//
// Created by wxudong on 17-12-16.
//


#include <cstring>
#include <stdlib.h>
#include <linux/time.h>
#include <time.h>
#include <utils/mylog.h>
#include <utils/zString.h>

#include "safekey_jni.h"
#include "utils/zJNIEnv.h"

extern jclass vskmClass;
extern jclass vsckmsClass;

int SafeKeyJni::encryptKey(char *input, int inputlen, char *output, int outputlen){
    return operatorKey(input, inputlen, output, outputlen, 0);
   /* for(int i = 0; i < inputlen; i++)
        output[i] = input[i] + (char)3;

    return 0;*/
}

int SafeKeyJni::decryptKey(char *input, int inputlen, char *output, int outputlen){
    return operatorKey(input, inputlen, output, outputlen, 1);
   /* for(int i = 0; i < inputlen; i++)
        output[i] = input[i] - (char)3;

    return 0;*/
}

int SafeKeyJni::operatorKey(char *input, int inputlen, char *output, int outputlen, int mode) {

    log("SafeKeyJni operatorKey start mode %d keylen %d", mode, inputlen);
    int ret = 0;
    // Guard: vskmClass may be NULL if xdja SDK was not found at JNI_OnLoad
    if (vskmClass == NULL) {
        log("operatorKey: vskmClass is NULL, xdja SafekeyManager not available");
        return -1;
    }
    zJNIEnv env;
    if(env.get() == NULL) {
        log("JNIEnv is NULL");
        return -1;
    }

    jbyteArray _input = env.get()->NewByteArray(inputlen);
    env.get()->SetByteArrayRegion(_input, 0, inputlen, (jbyte*)input);
    jbyteArray _output;
    jmethodID mid = NULL;
    if(mode == 0){
        mid = env.get()->GetStaticMethodID(vskmClass, "encryptKey", "([BI)[B");
    }else{
        mid = env.get()->GetStaticMethodID(vskmClass, "decryptKey", "([BI)[B");
    }
    // Guard: mid may be NULL if method signature doesn't match
    if (mid == NULL) {
        log("operatorKey: GetStaticMethodID returned NULL");
        if (env.get()->ExceptionCheck()) env.get()->ExceptionClear();
        env.get()->DeleteLocalRef(_input);
        return -1;
    }
    _output = (jbyteArray)env.get()->CallStaticObjectMethod(vskmClass, mid ,_input, inputlen);
    if (_output == NULL) {
        log("operatorKey: CallStaticObjectMethod returned NULL");
        env.get()->DeleteLocalRef(_input);
        return -1;
    }
    jbyte* a = env.get()->GetByteArrayElements(_output, JNI_FALSE);
    memcpy(output, a, (size_t)inputlen);

    for(int i=0; i<inputlen; i++){
        if(output[i] != 0){
            ret = 0;
            break;
        }
        ret = -1;
    }
    log("SafeKeyJni operatorKey ret = %d", ret);

    env.get()->ReleaseByteArrayElements(_output, a, 0);
    env.get()->DeleteLocalRef(_input);
    env.get()->DeleteLocalRef(_output);

    return ret;
}

int SafeKeyJni::getRandom(int len, char *random) {

    int ret = 0;
    log("SafeKeyJni getRandom start keylen %d", len);
    // Guard: vskmClass may be NULL
    if (vskmClass == NULL) {
        log("getRandom: vskmClass is NULL");
        return -1;
    }
    zJNIEnv env;
    if(env.get() == NULL) {
        log("JNIEnv is NULL");
        return -1;
    }

    jbyteArray _output;
    jmethodID mid = env.get()->GetStaticMethodID(vskmClass, "getRandom", "(I)[B");
    if (mid == NULL) {
        log("getRandom: GetStaticMethodID returned NULL");
        if (env.get()->ExceptionCheck()) env.get()->ExceptionClear();
        return -1;
    }
    _output = (jbyteArray)env.get()->CallStaticObjectMethod(vskmClass, mid, len);
    if (_output == NULL) {
        log("getRandom: result is NULL");
        return -1;
    }
    jbyte* a = env.get()->GetByteArrayElements(_output, JNI_FALSE);
    memcpy(random, a, (size_t)len);

    for(int i=0; i<len; i++){
        if(random[i] != 0){
            ret = 0;
            break;
        }
        ret = -1;
    }
    log("SafeKeyJni getRandom ret = %d", ret);

    env.get()->ReleaseByteArrayElements(_output, a, 0);
    env.get()->DeleteLocalRef(_output);
    return ret;
}


char *SafeKeyJni::ckmsencryptKey(char *input, int inputlen, uint32_t &outputlen) {
        return ckmsoperatorKey(input, inputlen, outputlen, 0);
    }

char *SafeKeyJni::ckmsdecryptKey(char *input, int inputlen, uint32_t &outputlen) {
        return ckmsoperatorKey(input, inputlen, outputlen, 1);
    }


char *SafeKeyJni::ckmsoperatorKey(char *input, int inputlen, uint32_t &outputlen,
                                  int mode) {
    // Guard: vsckmsClass may be NULL if xdja CKMS SDK is absent
    if (vsckmsClass == NULL) {
        log("ckmsoperatorKey: vsckmsClass is NULL");
        return nullptr;
    }
    zJNIEnv env;
    if (env.get() == NULL) {
        log("JNIEnv is NULL");
        return nullptr;
    }

    jbyteArray _input = env.get()->NewByteArray(inputlen);
    env.get()->SetByteArrayRegion(_input, 0, inputlen, (jbyte *) input);
    jbyteArray _output;
    jmethodID mid = NULL;
    if (mode == 0) {
        mid = env.get()->GetStaticMethodID(vsckmsClass, "ckmsencryptKey", "([BI)[B");
    } else if (mode == 1) {
        mid = env.get()->GetStaticMethodID(vsckmsClass, "ckmsdecrypeKey", "([BI)[B");
    }
    // Guard: mid NULL means method not found
    if (mid == NULL) {
        log("ckmsoperatorKey: GetStaticMethodID returned NULL for mode=%d", mode);
        if (env.get()->ExceptionCheck()) env.get()->ExceptionClear();
        env.get()->DeleteLocalRef(_input);
        return nullptr;
    }

    _output = (jbyteArray) env.get()->CallStaticObjectMethod(vsckmsClass, mid, _input, inputlen);
    if(NULL == _output) {
        log("ckms exception return null");
        env.get()->DeleteLocalRef(_input);
        return NULL;
    }
    jbyte *out = env.get()->GetByteArrayElements(_output, JNI_FALSE);
    outputlen = static_cast<uint32_t>(env.get()->GetArrayLength(_output));
    char *temp = (char *) malloc(outputlen);
    if (temp == NULL) {
        env.get()->ReleaseByteArrayElements(_output, out, 0);
        env.get()->DeleteLocalRef(_input);
        env.get()->DeleteLocalRef(_output);
        return NULL;
    }
    memcpy(temp, out, (size_t) outputlen);

    log("SafeKeyJni ckmsoperatorKey length = %d mode=%d", outputlen,mode);

    env.get()->ReleaseByteArrayElements(_output, out, 0);
    env.get()->DeleteLocalRef(_input);
    env.get()->DeleteLocalRef(_output);
    return temp;
}
