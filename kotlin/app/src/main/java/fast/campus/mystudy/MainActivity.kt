package fast.campus.mystudy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fast.campus.mystudy.Step1.*
import fast.campus.mystudy.Step3.ClosureTest
import fast.campus.mystudy.Step3.DSLTest
import fast.campus.mystudy.Step3.ExtFunctionTest
import fast.campus.mystudy.Step3.LambdasTest
import fast.campus.mystudy.step2.*
//import fast.campus.mystudy.step3.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 우리가 만든 예제들을 이곳에서 코딩

//        doTest(PolymorphTest(::WriteLn))
//        doTest(InterfaceAbstractTest(::WriteLn))
//        doTest(DataClassTest(::WriteLn))
//        doTest(ObjectTest(::WriteLn))
//        doTest(LambdasTest(::WriteLn))
//        doTest(ExtFunctionTest(::WriteLn))
//        doTest(ClosureTest(::WriteLn))
        doTest(DSLTest(::WriteLn))

        // using 확장함수
//        setClickHandler()
    }

    private fun doTest(o : TestClass){
        o.doTest()
    }

    fun WriteLn( any : Any) {
        txtMessage.text = "${txtMessage.text}${any.toString()}\n"
    }

}
