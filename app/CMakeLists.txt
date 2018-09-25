# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)


set(distribution_DIR ../../../../libs)


find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )





add_library( fmod
             SHARED
             IMPORTED )
set_target_properties( fmod
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libfmod.so )

add_library( fmodL
             SHARED
             IMPORTED )
set_target_properties( fmodL
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libfmodL.so )

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

add_library( # Sets the name of the library.
             qq_voice
             SHARED
             #src/main/cpp/play_sound.cpp
             src/main/cpp/common.cpp
             src/main/cpp/qq_voice.cpp
             src/main/cpp/common_platform.cpp
              )
include_directories(src/main/cpp/inc)


target_link_libraries( # Specifies the target library.
                       qq_voice
                       fmod
                       fmodL

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )