package fast.campus.myvision.api

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.upload_chooser.*

class UploadChooser : BottomSheetDialogFragment(){

    interface  UploadChooseNotifierInterface{
        fun cameraOnClick()
        fun gallaryOnClick()
    }

    var uploadChooserNotifierInterface : UploadChooseNotifierInterface? = null

    fun addNotifier(listener : UploadChooseNotifierInterface) {
        uploadChooserNotifierInterface = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.upload_chooser, container, false)
//        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListener()
    }

    private fun setupListener() {
        upload_camera.setOnClickListener{
            uploadChooserNotifierInterface?.cameraOnClick()
            //권한 작업 가능
        }

        upload_gallary.setOnClickListener{
         uploadChooserNotifierInterface?.gallaryOnClick()
        }
    }
}