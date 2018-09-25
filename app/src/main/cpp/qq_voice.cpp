//
// Created by westd on 2018/9/25.
//
#include <android/log.h>
#include <assert.h>
#include <malloc.h>
#include <stdlib.h>
#include <string.h>
#include <jni.h>
#include "inc/fmod.hpp"
#include "common.h"


#define TAG "ANDY_JNI"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
# define NELEM(x) ((int) (sizeof(x) / sizeof((x)[0])))

#define TYPE_PRIMARY 0
#define TYPE_LUOLI 1
#define TYPE_DASHU 2
#define TYPE_JINSONG 3
#define TYPE_GAOGUAI 4

int FMOD_Main()
{
    return 0;

}

JNIEXPORT void luoli(JNIEnv *env,jclass jobj,jstring path,jint type){
//    env->FindClass("jdkksd");
    LOGI("Play sound start ----------------------------播放开始");
    FMOD::System     *system;
    FMOD::Sound      *sound1;
    FMOD::Channel    *channel = 0;
    FMOD_RESULT       result;
    FMOD::DSP       *dsp;
    unsigned int      version;
    void             *extradriverdata = 0;

    Common_Init(&extradriverdata);
    result = FMOD::System_Create(&system);
    result = system->init(32, FMOD_INIT_NORMAL, extradriverdata);


    const char *path_str = env->GetStringUTFChars(path,NULL);

    //创建声音
    result = system->createSound(Common_MediaPath("buzaijian.mp3"), FMOD_DEFAULT, 0,&sound1);
    sound1->setMode(FMOD_LOOP_NORMAL);


    switch (type){
        case TYPE_PRIMARY:
            system->playSound(sound1,0, false,&channel);
            break;
        case TYPE_LUOLI:
            LOGI("%s","萝莉");
            //FMOD_DSP_TYPE_PITCHSHIFT提高音调，但是又不改变语速
            system->createDSPByType(FMOD_DSP_TYPE_PITCHSHIFT, &dsp);
            //2.0代表调高一个八度(12345671)
            dsp->setParameterFloat(FMOD_DSP_PITCHSHIFT_PITCH,2.5);
            system->playSound(sound1, NULL, false, &channel);
            channel->addDSP(0, dsp);
            break;
        case TYPE_DASHU:
            LOGI("%s","大叔");
            system->createDSPByType(FMOD_DSP_TYPE_PITCHSHIFT, &dsp);
            dsp->setParameterFloat(FMOD_DSP_PITCHSHIFT_PITCH,0.8);
            //system->createDSPByType(FMOD_DSP_TYPE_DELAY, &dsp);
            //dsp->setParameterFloat(FMOD_DSP_DELAY_CH0,200);
            system->playSound(sound1, NULL, false, &channel);
            channel->addDSP(0, dsp);
            break;
        case TYPE_JINSONG:
            LOGI("%s","惊悚");
            //FMOD_DSP_TYPE_TREMOLO 颤抖
            system->createDSPByType(FMOD_DSP_TYPE_TREMOLO, &dsp);
            dsp->setParameterFloat(FMOD_DSP_TREMOLO_SKEW,0.5);
            system->playSound(sound1, NULL, false, &channel);
            channel->addDSP(0, dsp);
            break;
        case TYPE_GAOGUAI:
            LOGI("%s","搞怪");
            //提高语速
            float frequency;
            system->createDSPByType(FMOD_DSP_TYPE_NORMALIZE, &dsp);
            system->playSound(sound1, NULL, false, &channel);
            channel->addDSP(0, dsp);
            channel->getFrequency(&frequency);
            frequency = static_cast<float>(frequency * 1.6);
            channel->setFrequency(frequency);
            break;
    }

    system->update();
    Common_Sleep(5000);


    env->ReleaseStringUTFChars(path,path_str);

    /*
        Shut down
    */
    result = sound1->release();
    ERRCHECK(result);
    result = system->close();
    ERRCHECK(result);
    result = system->release();
    ERRCHECK(result);

    Common_Close();
    LOGI("Play sound end");
}









static const JNINativeMethod gMethods[] = {
{
"luoli","(Ljava/lang/String;I)V",(void*)luoli
}
};

static int registerNatives(JNIEnv *env){
    LOGI("Register Begin-------------");
    jclass jclz;
    jclz = env->FindClass("com/example/westd/ndkapplication/changeVoice/VoiceUtils");

    if (jclz ==NULL){
        LOGI("Class didn't found!");
        return JNI_FALSE;
    }

    if (env->RegisterNatives(jclz,gMethods,NELEM(gMethods))<0){
        LOGI("Register Error!");
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

JNIEXPORT jint JNI_OnLoad(JavaVM * vm,void* reserved){
    LOGI("onload begin!");

    JNIEnv *env = NULL;
    jint  result = -1;


    if (vm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {
        LOGI("ERROR: GetEnv failed\n");
        return -1;
    }
    assert(env != NULL);

    registerNatives(env);

    return JNI_VERSION_1_4;

}
