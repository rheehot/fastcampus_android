package fast.campus.myvision.api

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import android.widget.ImageView
import com.google.api.services.vision.v1.Vision
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse
import com.google.api.services.vision.v1.model.EntityAnnotation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_analyze_view.*
import java.io.File
import java.lang.ref.WeakReference
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private val CAMERA_PERMISSION_REQUEST = 1000
    private val GALLERY_PERMISSION_REQUEST = 1001
    private val FILE_NAME = "picture.jpg"
    private var uploadChooser: UploadChooser? = null
//    private val activity: MainActivity
    val LABEL_DETECTION_REQUEST = "label_detection_request"
    val LANDMARK_DETECTION_REQUEST = "landmark_detection_request"
    private var requestType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListener()
    }

    private fun setupListener() {
        upload_image.setOnClickListener {
            uploadChooser = UploadChooser().apply {
                addNotifier(object : UploadChooser.UploadChooseNotifierInterface {
                    override fun cameraOnClick() {
                        Log.d("upload", "cameraOnClick")
                        checkCameraPermission()
                    }

                    override fun galleryOnClick() {
                        Log.d("upload", "galleryOnClick")
                        checkGalleryPermission()
                    }
                })
            }
            uploadChooser!!.show(supportFragmentManager, "")
        }
    }

//    private fun setupListener() {
//
//        upload_image.setOnClickListener {
//
//            //            UploadChooser().show(supportFragmentManager, "")
//            UploadChooser().apply {
//                addNotifier(object : UploadChooser.UploadChooseNotifierInterface {
//
//                    override fun cameraOnClick() {
////                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                        Log.d("upload", "cameraOnClick")
//                        //카메라 권한
//                        checkCameraPermission()
//                    }
//
//                    override fun gallaryOnClick() {
//                        Log.d("upload", "gallaryOnClick")
//                        //Storage 읽기 권한이 더 맞음
//                        checkGalleryPermission()
//                    }
//                })
//            }.show(supportFragmentManager, "")
//        }
//    }

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
//        //1
        uploaded_image.setImageBitmap(bitmap)
//        //2
//        uploaded_image.setImageBitmap(MediaStore.Images.Media.getBitmap(contentResolver,imageUri))

        uploadChooser?.dismiss()

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

    private fun requestCloudVisionApi(bitmap: Bitmap, requestType: String) {
//        labelDetectionTask?.requestCloudVisionApi(bitmap, object : LabelDetectionTask
//        .LabelDetectionNotifierInterface {
//            override fun notifiyResult(result: String) {
//                uploaded_image_result.text = result
//            }
//        }, requestType)
    }

    inner class ImageRequestTask constructor(
        activity: MainActivity,val request: Vision.Images.Annotate
    ) : AsyncTask<Any, Void, String>() {

        private val weakReference: WeakReference<MainActivity>

        init {
            weakReference = WeakReference(activity)
        }

        override fun doInBackground(vararg params: Any?): String {
            try {
                val response = request.execute()
                return findProperResponseType(response)

            } catch (e: Exception) {
            }
            return "분석 실패"
        }

        override fun onPostExecute(result: String?) {
            val activity = weakReference.get()
//            if (activity != null && !activity.isFinishing) {
//                result?.let { labelDetectionNotifierInterface?.notifiyResult(it) }
//            }
        }
    }

    private fun convertResponseToString(labels: List<EntityAnnotation>): String {
        val message = StringBuilder("분석 결과\n")
        labels.forEach {
            message.append(String.format(Locale.US, "%.3f: %s", it.score, it.description))
            message.append("\n")
        }
        return message.toString()
    }

    private fun findProperResponseType(response: BatchAnnotateImagesResponse): String {
        when (requestType) {
            activity.LABEL_DETECTION_REQUEST -> {
                return convertResponseToString(response.responses[0].labelAnnotations)
            }
            activity.LANDMARK_DETECTION_REQUEST -> {
                return convertResponseToString(response.responses[0].landmarkAnnotations)
            }
        }
        return "분석 실패"
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
