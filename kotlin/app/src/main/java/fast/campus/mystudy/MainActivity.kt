package fast.campus.mystudy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fast.campus.mystudy.Step1.ExceptionTest
import fast.campus.mystudy.Step1.FirstClass
import fast.campus.mystudy.Step1.NumberTest
import fast.campus.mystudy.step2.ClassTest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 우리가 만든 예제들을 이곳에서 코딩

        doTest(ClassTest(::WriteLn))
    }

    private fun doTest(o : TestClass){
        o.doTest()
    }

    fun WriteLn( any : Any) {
        txtMessage.text = "${txtMessage.text}${any.toString()}\n"
    }
}
