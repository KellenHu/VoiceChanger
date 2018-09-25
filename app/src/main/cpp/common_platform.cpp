#include "common.h"
#include <string.h>
#include <jni.h>
#include <unistd.h>
#include <string>
#include <vector>

#ifndef _Included_com_example_westd_ndkapplication_changeVoice_MainActivity
#define _Included_com_example_westd_ndkapplication_changeVoice_MainActivity
#endif
JNIEnv *gJNIEnv;
jobject gMainActivityObject;
int gDownButtons;
int gLastDownButtons;
int gPressedButtons;
bool gSuspendState;
bool gQuitState;
std::string gUIString;
std::vector<char *> gPathList;

int FMOD_Main(); // Defined in example

void Common_Init(void **extraDriverData)
{
	gDownButtons = 0;
	gLastDownButtons = 0;
	gPressedButtons = 0;
	gSuspendState = false;
	gQuitState = false;
}

void Common_Close()
{
    for (std::vector<char *>::iterator item = gPathList.begin(); item != gPathList.end(); ++item)
    {
        free(*item);
    }

    gPathList.clear();
}

void Common_Update()
{
	jstring text = gJNIEnv->NewStringUTF(gUIString.c_str());
	jclass mainActivityClass = gJNIEnv->GetObjectClass(gMainActivityObject);
    jmethodID updateScreenMethodID = gJNIEnv->GetMethodID(mainActivityClass, "updateScreen", "(Ljava/lang/String;)V");

    gJNIEnv->CallVoidMethod(gMainActivityObject, updateScreenMethodID, text);

    gJNIEnv->DeleteLocalRef(text);
    gJNIEnv->DeleteLocalRef(mainActivityClass);

    gUIString.clear();

    gPressedButtons = (gLastDownButtons ^ gDownButtons) & gDownButtons;
    gLastDownButtons = gDownButtons;

    if (gQuitState)
    {
    	gPressedButtons |= (1 << BTN_QUIT);
    }
}

void Common_Sleep(unsigned int ms)
{
    usleep(ms * 1000);
}

void Common_Exit(int returnCode)
{
    exit(returnCode);
}

void Common_DrawText(const char *text)
{
    char s[256];
    snprintf(s, sizeof(s), "%s\n", text);
    
    gUIString.append(s);
}

void Common_LoadFileMemory(const char *name, void **buff, int *length)
{
    FILE *file = fopen(name, "rb");
    
    fseek(file, 0, SEEK_END);
    long len = ftell(file);
    fseek(file, 0, SEEK_SET);
    
    void *mem = malloc(len);
    fread(mem, 1, len, file);
    
    fclose(file);

    *buff = mem;
    *length = len;
}

void Common_UnloadFileMemory(void *buff)
{
    free(buff);
}

bool Common_BtnPress(Common_Button btn)
{
    return ((gPressedButtons & (1 << btn)) != 0);
}

bool Common_BtnDown(Common_Button btn)
{
    return ((gDownButtons & (1 << btn)) != 0);
}

const char *Common_BtnStr(Common_Button btn)
{
    switch (btn)
    {
        case BTN_ACTION1: return "A";
        case BTN_ACTION2: return "B";
        case BTN_ACTION3: return "C";
        case BTN_ACTION4: return "D";
        case BTN_UP:      return "Up";
        case BTN_DOWN:    return "Down";
        case BTN_LEFT:    return "Left";
        case BTN_RIGHT:   return "Right";
        case BTN_MORE:    return "E";
        case BTN_QUIT:    return "Back";
        default:          return "Unknown";
    }
}

const char *Common_MediaPath(const char *fileName)
{
    char *filePath = (char *)calloc(256, sizeof(char));

    strcat(filePath, "file:///android_asset/");
    strcat(filePath, fileName);
    gPathList.push_back(filePath);

    return filePath;
}

const char *Common_WritePath(const char *fileName)
{
	return Common_MediaPath(fileName);
}

bool Common_SuspendState()
{
	return gSuspendState;
}
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT
jstring
JNICALL
Java_com_example_westd_ndkapplication_changeVoice_MainActivity_getButtonLabel(JNIEnv *env, jobject thiz, jint index)
{
    return env->NewStringUTF(Common_BtnStr((Common_Button)index));
}

JNIEXPORT void JNICALL Java_com_example_westd_ndkapplication_changeVoice_MainActivity_buttonDown(JNIEnv *env, jobject thiz, jint index)
{
    gDownButtons |= (1 << index);
}

JNIEXPORT void JNICALL Java_com_example_westd_ndkapplication_changeVoice_MainActivity_buttonUp(JNIEnv *env, jobject thiz, jint index)
{
    gDownButtons &= ~(1 << index);
}

JNIEXPORT void JNICALL Java_com_example_westd_ndkapplication_changeVoice_MainActivity_setStateCreate(JNIEnv *env, jobject thiz)
{

}

JNIEXPORT void JNICALL Java_com_example_westd_ndkapplication_changeVoice_MainActivity_setStateStart(JNIEnv *env, jobject thiz)
{
	gSuspendState = false;
}

JNIEXPORT void JNICALL Java_com_example_westd_ndkapplication_changeVoice_MainActivity_setStateStop(JNIEnv *env, jobject thiz)
{
	gSuspendState = true;
}

JNIEXPORT void JNICALL Java_com_example_westd_ndkapplication_changeVoice_MainActivity_setStateDestroy(JNIEnv *env, jobject thiz)
{
	gQuitState = true;
}

JNIEXPORT void JNICALL Java_com_example_westd_ndkapplication_changeVoice_MainActivity_main(JNIEnv *env, jobject thiz)
{
	gJNIEnv = env;
	gMainActivityObject = thiz;

	FMOD_Main();
}
#ifdef __cplusplus
}
#endif

 /* extern "C" */
