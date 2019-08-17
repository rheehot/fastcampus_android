package fast.campus.mystudy.Step1

import fast.campus.mystudy.TestClass

class FirstClass( p : (Any)->Unit) : TestClass(p){
    override fun doTest() {
        // 1. 종결자를 사용하지 않아도 된다.
        // 2. 형을 선언하지않았다면 변수를 대입시 형이 결정된다.
        // 3. 변수는 읽기전용과 읽기쓰기 상태로 선언된다.
        // 4. null은 엄격히 구분한다. -> null을 허용하는 변수는 ?로 정의
        // 5. scope는 java와 유사하다.

        // 1
        println("종결자(;)없어도 됩니다. ")
        println("var는 읽기쓰기. val는 읽기모드")

        // 2. 형이 결정되면 다른 형으로 변환이 불가함
        var nCount : Int = 0
        var name         = "PSW"

        // 3. 읽기전용과 읽고쓰기 전용
        val age = 0
        //age++

        // 4. null이 가능하다면 엘비스를 불러주세요
        //var MyMoney : Int = null

        // 5. 모든 것은 먼저 선언되어 있어야 이해할 수 있다.
        //println(message)

        val message = "위에서는 액세스 불가함"
        // 5. 어떻게 변수를 이해했을까?
        println(hiMessage)
    }

    val hiMessage : String = "Hi"
}