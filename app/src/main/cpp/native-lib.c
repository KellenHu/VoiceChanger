//
// Created by westd on 2017/12/10.
//
#include "com_example_westd_ndkapplication_FileUtils.h"
#include <android/log.h>
#include <assert.h>
#include <malloc.h>
#include <stdlib.h>
#include <string.h>

//int __android_log_print(int prio, const char* tag, const char* fmt, ...)
#define TAG "ANDY_JNI"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
# define NELEM(x) ((int) (sizeof(x) / sizeof((x)[0])))


/**
 * 获取文件大小
 * @param path
 * @return
 */
long get_file_size(const char* path){
    FILE *fp = fopen(path,"rb");
    fseek(fp,0,SEEK_END);
    long lengh = ftell(fp);
    fclose(fp);
    return lengh;
}

JNIEXPORT void JNICALL native_diff
(JNIEnv *env, jobject obj, jstring path, jstring parttern_path, jint num){

    LOGI("JNI Native diff begin!");
    const char *path_str = (*env)->GetStringUTFChars(env,path,NULL);
    const char*pattern_path_str = (*env)->GetStringUTFChars(env,parttern_path,NULL);

    //申请一个二维数组(存放文件名)
    char **patches = (char **)malloc(sizeof(char) * num);

    for (int i = 0; i < num; i++){
        //存放文件名的地址
        patches[i] = (char *)malloc(sizeof(char *) * 100);
        memset(patches[i],0, sizeof(char *)*100);
        //需要分割的文件 video.mp4
        sprintf(patches[i],pattern_path_str,i);//格式化文件名
        LOGI("patch path :: %s",patches[i]);
    }

    long file_size = get_file_size(path_str);
    FILE * fpr = fopen(path_str,"rb");


    /**
     *
     * 1. 判断文件大小能否被File——NUm整除 即是否能被均分
     * 2. 如果能整除就均分
     * 3. 不能的话就先均分file——num - 1
     */
    if (file_size & num == 0){
        long part = file_size/num;
        for (int i=0 ;i<num;i++){
            FILE *fpw = fopen(patches[i],"wb");//文件存在只允许写
            for (int j = 0; j < part; ++j) {
                fputc(fgetc(fpr),fpw);
            }
            fclose(fpw);
        }

    } else{
        long part = file_size/(num-1);
        for (int i=0 ;i<num-1;i++){
            FILE *fpw = fopen(patches[i],"wb");//文件存在只允许写
            for (int j = 0; j < part; ++j) {
                fputc(fgetc(fpr),fpw);
            }
        }
        FILE *fpw = fopen(patches[num-1],"wb");
        for (int j = 0; j < file_size/(num-1); ++j) {
            fputc(fgetc(fpr),fpw);
        }
        fclose(fpw);

    }
    //关闭文件
    fclose(fpr);

    //Free 申请的数组
    for (int i = 0; i < num; ++i) {
        free(patches[i]);
    }

    free(patches);



    (*env)->ReleaseStringUTFChars(env,path,path_str);
    (*env)->ReleaseStringUTFChars(env,parttern_path,pattern_path_str);

}



JNIEXPORT void JNICALL native_patch
        (JNIEnv *env, jobject obj, jstring merge_path,
         jstring pattern_Path, jint file_num){

    LOGI("JNI native patch begin");
    const char *path_Str = (*env) -> GetStringUTFChars(env, merge_path, NULL);
    const char *pattern_Path_str = (*env) ->GetStringUTFChars(env, pattern_Path, NULL);

    //申请二维字符数据, 存放子文件名
    char **patches = (char **)malloc(sizeof(char *) * file_num);

    int i =0;
    for (; i < file_num; i++) {
        //每个文件名申请地址
//        LOGI("char = %d char * = %d", sizeof(char), sizeof(char *));
        patches[i] = (char*) malloc(sizeof(char) * 100);
        // 需要分割的文件 Vibrato.mp4
        // 每个子文件名称 Vibrato_n.mp4
        sprintf(patches[i], pattern_Path_str, i);// 格式化文件名
        LOGI("patch path : %s",patches[i]);
    }

    FILE *fpw = fopen(path_Str, "wb");

    for (int i =0; i < file_num; i++) {
        int filesize = get_file_size(patches[i]);
        FILE *fpr = fopen(patches[i], "rb");
        for (int j =0; j < filesize; j++) {
            fputc(fgetc(fpr), fpw);
        }
        fclose(fpr);
    }
    fclose(fpw);

    for (int i =0; i< file_num; i++) {
        free(patches[i]); //每一个malloc 对应一个free
    }
    free(patches);
    (*env)->ReleaseStringUTFChars(env, merge_path, path_Str);
    (*env)->ReleaseStringUTFChars(env, pattern_Path, pattern_Path_str);
}



static const JNINativeMethod gMethods[] = {
        {
                "diff","(Ljava/lang/String;Ljava/lang/String;I)V",(void*)native_diff
        },
        {
            "patch","(Ljava/lang/String;Ljava/lang/String;I)V",(void*)native_patch
        }
};

static int registerNatives(JNIEnv *env){
    LOGI("Register Begin-------------");
    jclass jclz;
    jclz = (*env)->FindClass(env,"com/example/westd/ndkapplication/FileUtils");

    if (jclz ==NULL){
        LOGI("Class didn't found!");
        return JNI_FALSE;
    }

    if ((*env)->RegisterNatives(env,jclz,gMethods,NELEM(gMethods))<0){
        LOGI("Register Error!");
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

JNIEXPORT jint JNI_OnLoad(JavaVM * vm,void* reserved){
    LOGI("onload begin!");

    JNIEnv *env = NULL;
    jint  result = -1;


    if ((*vm)->GetEnv(vm,(void**) &env, JNI_VERSION_1_4) != JNI_OK) {
        LOGI("ERROR: GetEnv failed\n");
        return -1;
    }
    assert(env != NULL);

    registerNatives(env);

    return JNI_VERSION_1_4;

}

int addTest(int a,int b){
    LOGI("第三方 SO 调用测试 %d",a+b);
    return  a+b;
}