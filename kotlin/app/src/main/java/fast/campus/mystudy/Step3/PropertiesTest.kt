package fast.campus.mystudy.Step3

import fast.campus.mystudy.TestClass

//fun main (args : Array <String>){

class PropertiesTest (p : (Any) -> Unit) : TestClass(p){

    override fun doTest() {
//    kotlin에서는 properties를 사용할 수 있다.
//    변수를 가져오거나 대입할 때 함수처럼 프로그래밍이 가능한 것
//    properties는 자바에서 사용하는 것과 유사하다. 변수를 좀더 강력(로직을 가미)하게 관리할 수 있다.
//    get(), set() 함수로 읽고쓰기를 관리한다.
//    자기자신을 가리키는 예약어는 field이다.

        passwd = "1234567867657572313"
        println (passwd)

        // 내가 만든 클래스에 확장 프로퍼티 추가
        val empty = EmptyClass()
        empty.newProp = "안녕"
        empty.newProp = "반가와요"
        empty.newProp = "새로운 클래스"
        println (empty.newProp)

        // mutableList에 확장 프로퍼티 추가
        // Queue와 흡사한 Log처리
        val mLog = mutableListOf <String>("success:100", "pass:203", "fail:001")
        println(mLog)

        mLog.firstHead = "success:100"
        mLog.firstHead = "shutdown:-1"
        mLog.forEach { println(">${it}"); }
        println(mLog.firstHead)
    }


    var passwd : String = ""
        get(){
            if(field.length > 5) {
                field = "길이를 초과했음"
            }
            return field
        }
        set(s : String ){
            kotlin.io.println("\"${s}\"을 저장함")
            field = s
        }

    class EmptyClass {
        var message : String = ""
    }

    var EmptyClass?.newProp : String
        get(){
            return this!!.message
        }
        set(value) { this!!.message = value + "-" + this!!.message}

    // MutableList에 firstHead 프로퍼티 추가
    var MutableList<String>?.firstHead : String
        get() {
            return this!!.get(0) //첫번째 값만 가져옴
        }
        set(value) {
            this!!.add(0, value)
        }
}
