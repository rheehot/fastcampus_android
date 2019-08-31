package fast.campus.myvision.api

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private val CAMERA_PERMISSION_REQUEST = 1000
    private val GALLARY_PERMISSION_REQUEST = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListener()
    }

    private fun setupListener(){

      upload_image.setOnClickListener{

//            UploadChooser().show(supportFragmentManager, "")
            UploadChooser().apply {
                addNotifier(object : UploadChooser.UploadChooseNotifierInterface{

                    override fun cameraOnClick() {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        Log.d("upload","cameraOnClick")
                        //카메라 권한
                        checkCameraPermission()
                    }

                    override fun gallaryOnClick() {
                        Log.d("upload","gallaryOnClick")
                        //Storage 읽기 권한이 더 맞음
                        checkGallaryPermission()
                    }
                })
            }.show(supportFragmentManager, "")
        }
    }

    private fun checkCameraPermission() {
        PermissionUtil().requestPermission(
            this, CAMERA_PERMISSION_REQUEST, android.Manifest.permission.CAMERA
        )
    }
    private fun checkGallaryPermission(){
        PermissionUtil().requestPermission(
            this,
            GALLARY_PERMISSION_REQUEST,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
        )
    }
}
