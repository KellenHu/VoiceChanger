package com.example.westd.ndkapplication;

/**
 * Created by westd on 2017/12/10.
 */

public class FileUtils {

  static {
//    System.loadLibrary("native-lib");
  }

  public  static  native void diff(String path,String pattern_Path,
                                   int file_num);


  public  static  native void patch(String mergePath,String pattern_Path,
                                   int file_num);
//  ClassLoader
}
