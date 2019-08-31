package fast.campus.myvision.api

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
                    }

                    override fun gallaryOnClick() {
                        Log.d("upload","gallaryOnClick")
                    }
                })
            }.show(supportFragmentManager, "")
        }
    }
}
