package com.example.westd.ndkapplication.changeVoice;

public class VoiceUtils {

  public static final int MODE_PRIMARY = 0;
  public static final int MODE_LUOLI = 1;
  public static final int MODE_DASHU = 2;
  public static final int MODE_JINSONG = 3;
  public static final int TYPE_GAOGUAI = 4;
//  public static final int MODE_PRIMARY = 0;


  public static native void luoli(String path,int type);

  static {
    /*
     * To simplify our examples we try to load all possible FMOD
     * libraries, the Android.mk will copy in the correct ones
     * for each example. For real products you would just load
     * 'fmod' and if you use the FMOD Studio tool you would also
     * load 'fmodstudio'.
     */

    // Try debug libraries...
    try {
      System.loadLibrary("fmod");
      System.loadLibrary("fmodL");
      System.loadLibrary("qq_voice");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
