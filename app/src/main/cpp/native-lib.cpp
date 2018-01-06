#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL Java_com_qing_codeart_demo_MainActivity_stringFromJNI(
        JNIEnv *env, jobject obj) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
