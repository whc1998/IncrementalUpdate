package com.create.whc.incrementalupdate;

public class Bspatch {

    static {
        System.loadLibrary("native-lib");
    }

    //合并差分包
    public static native void bspatch(String oldPath,String newPath,String patchPath);

    //生成差分包
    public static native void bsdiff(String oldPath,String newPath,String patchPath);

}
