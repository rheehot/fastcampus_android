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
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.vision.v1.Vision
import com.google.api.services.vision.v1.VisionRequest
import com.google.api.services.vision.v1.VisionRequestInitializer
import com.google.api.services.vision.v1.model.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_analyze_view.*
import java.io.ByteArrayOutputStream
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

    private val CLOUD_VISION_API_KEY = "AIzaSyD4Icf8afoiO4n--48I58gRe6B320UuZhE"
    private val ANDROID_PACKAGE_HEADER = "X-Android-Package"
    private val ANDROID_CERT_HEADER = "X-Android_Cert"
    private val MAX_RESULTS = 10

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
//
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
        requestCloudVisionApi(bitmap)

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

    private fun requestCloudVisionApi(
        bitmap: Bitmap
    ) {
        val visionTask = ImageRequestTask(this, prepareImageRequest(bitmap))
        visionTask.execute()
    }

    private fun prepareImageRequest(bitmap: Bitmap): Vision.Images.Annotate {
        val httpTransport = AndroidHttp.newCompatibleTransport()
        val jsonFactory = GsonFactory.getDefaultInstance()

        val requestInitializer = object : VisionRequestInitializer(CLOUD_VISION_API_KEY) {
            override fun initializeVisionRequest(request: VisionRequest<*>?) {
                super.initializeVisionRequest(request)
                val packageName = packageName
                request?.requestHeaders?.set(ANDROID_PACKAGE_HEADER, packageName)
                val sig = PackageManagerUtil().getSignature(packageManager, packageName)
                request?.requestHeaders?.set(ANDROID_CERT_HEADER, sig)
            }
        }
        val builder = Vision.Builder(httpTransport, jsonFactory, null)
        builder.setVisionRequestInitializer(requestInitializer)
        val vision = builder.build()

        val batchAnnotateImageRequest = BatchAnnotateImagesRequest()
        batchAnnotateImageRequest.requests = object : ArrayList<AnnotateImageRequest>() {
            init {
                val annotateImageRequest = AnnotateImageRequest()

                val base64EncodedImage = Image()
                val byteArrayOutputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream)
                val imageBytes = byteArrayOutputStream.toByteArray()

                base64EncodedImage.encodeContent(imageBytes)
                annotateImageRequest.image = base64EncodedImage

                annotateImageRequest.features = object : ArrayList<Feature>() {
                    init {
                        val labelDetection = Feature()
                        when (requestType) {
                            LABEL_DETECTION_REQUEST -> {
                                labelDetection.type = "LABEL_DETECTION"
                            }
                            LANDMARK_DETECTION_REQUEST -> {
                                labelDetection.type = "LANDMARK_DETECTION"
                            }
                        }
                        labelDetection.maxResults = MAX_RESULTS
                        add(labelDetection)
                    }
                }
                add(annotateImageRequest)
            }
        }
        val annotateRequest = vision.images().annotate(batchAnnotateImageRequest)
        annotateRequest.setDisableGZipContent(true)
        return annotateRequest
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
//                return findProperResponseType(response)
                return convertResponseToString(response)

            } catch (e: Exception) {
            }
            return "분석 실패"
        }

        override fun onPostExecute(result: String?) {

            val activity = weakReference.get()
            if (activity != null && !activity.isFinishing) {
                uploaded_image_result.text = result
//                result?.let { labelDetectionNotifierInterface?.notifiyResult(it) }
            }
        }
    }

//    private fun convertResponseToString(labels: List<EntityAnnotation>): String {
//        val message = StringBuilder("분석 결과\n")
//        labels.forEach {
//            message.append(String.format(Locale.US, "%.3f: %s", it.score, it.description))
//            message.append("\n")
//        }
//        return message.toString()
//    }

        private fun convertResponseToString(response: BatchAnnotateImagesResponse): String {
            val message = StringBuilder("분석 결과\n")
            val labels = response.responses[0].labelAnnotations
            labels?.let {
                it.forEach {
                    message.append(String.format(Locale.US, "%.3f: %s", it.score, it.description))
                    message.append("\n")
                }
                return message.toString()
            }
            return "분석 실패"
        }

//    private fun findProperResponseType(response: BatchAnnotateImagesResponse): String {
//        when (requestType) {
//            activity.LABEL_DETECTION_REQUEST -> {
//                return convertResponseToString(response.responses[0].labelAnnotations)
//            }
//            activity.LANDMARK_DETECTION_REQUEST -> {
//                return convertResponseToString(response.responses[0].landmarkAnnotations)
//            }
//        }
//        return "분석 실패"
//    }

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
