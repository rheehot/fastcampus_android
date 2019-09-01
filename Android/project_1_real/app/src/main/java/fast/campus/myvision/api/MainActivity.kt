package fast.campus.myvision.api

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_analyze_view.*
import java.io.File
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private val CAMERA_PERMISSION_REQUEST = 1000
    private val GALLERY_PERMISSION_REQUEST = 1001
    private val FILE_NAME = "picture.jpg"
    private var uploadChooser: UploadChooser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListener()
    }

    private fun setupListener() {

        upload_image.setOnClickListener {

            //            UploadChooser().show(supportFragmentManager, "")
            UploadChooser().apply {
                addNotifier(object : UploadChooser.UploadChooseNotifierInterface {

                    override fun cameraOnClick() {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        Log.d("upload", "cameraOnClick")
                        //카메라 권한
                        checkCameraPermission()
                    }

                    override fun gallaryOnClick() {
                        Log.d("upload", "gallaryOnClick")
                        //Storage 읽기 권한이 더 맞음
                        checkGalleryPermission()
                    }
                })
            }.show(supportFragmentManager, "")
        }
    }

    private fun checkCameraPermission() {
        if (PermissionUtil().requestPermission(
                this,
                CAMERA_PERMISSION_REQUEST,
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) openCamera()
    }

    private fun checkGalleryPermission() {
        if (PermissionUtil().requestPermission(
                this,
                GALLERY_PERMISSION_REQUEST,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) openGallery()
    }

    private fun openCamera() {

        //사진 저장 위치
        val photoUri = FileProvider.getUriForFile(
            this,
            applicationContext.packageName + ".provider",
            createCameraFile()
        )
        startActivityForResult(
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                putExtra(MediaStore.EXTRA_OUTPUT, photoUri) //초기화 후 사진 저장
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }, CAMERA_PERMISSION_REQUEST
        )
    }

    private fun openGallery() {
        val intent = Intent().apply {
            setType("image/*")
            setAction(Intent.ACTION_GET_CONTENT)
        }
        startActivityForResult(
            Intent.createChooser(intent, "Select a photo"),
            GALLERY_PERMISSION_REQUEST
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST -> {
                if (resultCode != Activity.RESULT_OK) return
                val photoUri = FileProvider.getUriForFile(
                    this,
                    applicationContext.packageName + ".provider",
                    createCameraFile()
                )
                uploadImage(photoUri)
            }
            GALLERY_PERMISSION_REQUEST -> data?.let { uploadImage(it.data) }
        }
    }

    private fun uploadImage(imageUri: Uri) {
        val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
        //1
        uploaded_image.setImageBitmap(bitmap)
        //2
        uploaded_image.setImageBitmap(MediaStore.Images.Media.getBitmap(contentResolver,imageUri))

//        uploadChooser?.dismiss()
//        DetectionChooser().apply {
//            addDetectionChooserNotifierInterface(object : DetectionChooser
//            .DetectionChooserNotifierInterface {
//                override fun detectLabel() {
//                    findViewById<ImageView>(R.id.uploaded_image).setImageBitmap(bitmap)
//                    requestCloudVisionApi(bitmap, LABEL_DETECTION_REQUEST)
//
//                }
//
//                override fun detectLandmark() {
//                    findViewById<ImageView>(R.id.uploaded_image).setImageBitmap(bitmap)
//                    requestCloudVisionApi(bitmap, LANDMARK_DETECTION_REQUEST)
//                }
//            })
//        }.show(supportFragmentManager, "")

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            GALLERY_PERMISSION_REQUEST -> {
                if (PermissionUtil().permissionGranted(
                        requestCode,
                        GALLERY_PERMISSION_REQUEST,
                        grantResults
                    )
                ) openGallery()
            }
            CAMERA_PERMISSION_REQUEST -> {
                if (PermissionUtil().permissionGranted(
                        requestCode,
                        CAMERA_PERMISSION_REQUEST,
                        grantResults
                    )
                ) openCamera()
            }
        }
    }


    private fun createCameraFile(): File {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File(dir, FILE_NAME)
    }

}
