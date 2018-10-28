package com.create.whc.incrementalupdate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bspatch.setOnClickListener {
            //            if (getVersionCode() < 2) {
            Log.d("harrison", "当前版本号小于2 进行增量更新")
            doBspatchTask()
            Toast.makeText(this, "合并差分包", Toast.LENGTH_SHORT).show()
//            }
        }

        bsdiff.setOnClickListener {
            val thred = Runnable {
                run {
                    var oldPath = OLD_APK_FILE
                    Log.d("harrison", "oldpath=${oldPath}")
                    var newPath = DOWNLOAD_APK
                    Log.d("harrison", "newPath=${newPath}")
                    var patchPath = PATCH_FILE_PATH
                    Log.d("harrison", "patchPath=${patchPath}")
                    Bspatch.bsdiff(oldPath, newPath, patchPath)
                    Log.d("harrison", "bsdiff success")
                }
            }

            Thread(thred).start()
            Toast.makeText(this, "生成差分包", Toast.LENGTH_SHORT).show()
        }

        image.setOnClickListener {
            val intent = Intent(this,SecondActivity().javaClass)
            startActivity(intent)
            Toast.makeText(this, "跳转", Toast.LENGTH_SHORT).show()
        }

    }

    private fun doBspatchTask() {
//        async {
//            val bytes = URL(URL_PATCH_DOWNLOAD).readBytes()
//            val patchFile = File(Environment.getExternalStorageDirectory(), PATCH_FILE)
//            if (patchFile.exists()) {
//                patchFile.delete()
//            }
//            patchFile.writeBytes(bytes)
//
//            var oldPath = this@MainActivity.getApkSourceDir(PACKAGE_NAME)
//            var newPath = NEW_APK_PATH
//            var patchPath = patchFile.absolutePath
//
//            Bspatch.bspatch(oldPath, newPath, patchPath)
//
//            uiThread {
//                this@MainActivity.installApk(newPath)
//            }
//
//        }

        val thred = Runnable {
            run {
                var oldPath = this@MainActivity.getApkSourceDir(PACKAGE_NAME)
                Log.d("harrison", "oldpath=${oldPath}")
                var newPath = NEW_APK_PATH
                Log.d("harrison", "newPath=${newPath}")
                var patchPath = PATCH_FILE_PATH
                Log.d("harrison", "patchPath=${patchPath}")

                Bspatch.bspatch(oldPath, newPath, patchPath)
                this@MainActivity.installApk(newPath)
            }
        }

        Thread(thred).start()

    }

}
