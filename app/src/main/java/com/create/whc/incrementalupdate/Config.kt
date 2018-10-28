package com.create.whc.incrementalupdate

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.support.v4.content.FileProvider
import android.util.Log
import java.io.File

val PATCH_FILE = "apk.patch"

val PACKAGE_NAME = "com.create.whc.incrementalupdate"

val SD_CARD = Environment.getExternalStorageDirectory().absolutePath + File.separator

val NEW_APK_PATH = SD_CARD + "TestNewApk.apk"

val DOWNLOAD_APK = SD_CARD + "newApk.apk"

val PATCH_FILE_PATH = SD_CARD + PATCH_FILE

val OLD_APK_FILE = SD_CARD + "oldApk.apk"

fun Context.getVersionCode(): Int {
    val packageMannager = this.packageManager
    val packageInfo = packageMannager.getPackageInfo(this.packageName, 0)
    Log.d("harrison", "versionCode=${packageInfo.versionCode}")
    return packageInfo.versionCode
}

//获取当前data/app 源文件的路径
fun Context.getApkSourceDir(packageName: String): String {
    val appInfo = this.packageManager.getApplicationInfo(packageName, 0)
    return appInfo.sourceDir
}

//安装apk
fun Context.installApk(apkPath: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    val apkFile=File(apkPath)
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        val contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", apkFile)
        intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
    }else{
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive")
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    this.startActivity(intent)
}