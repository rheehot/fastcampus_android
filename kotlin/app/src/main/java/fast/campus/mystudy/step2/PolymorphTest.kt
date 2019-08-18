package fast.campus.mystudy.step2

import fast.campus.mystudy.TestClass

class PolymorphTest( p : (Any) -> Unit) : TestClass(p){

    override fun doTest() {
        var obj1 = baseClass2()
        obj1.f1()
        var obj2 = childClass()
        obj2.f1()
        obj2.f2()
        obj2.f2("문자열 파라메터")
        obj2.f2("문자열 파라메터", 100)

        //연산자 오버로링 예제
        var s = Student("박모씨")
        (0..99).forEach {s++}
        s.printMe()

        var s2 = Student("김모씨")
        s2.nScore = 50

        kotlin.io.println("두 학생의 점수는 ${s + s2}")
    }

    open class baseClass2{

        // 상속받은 클래스에서 override 하려면 부모클래스에서 open으로 정의.
        open var name = "base"
        open fun f1() = kotlin.io.println(this.toString())

        // 외부사용금지 .찍고 메소드 사용못함.
        private fun onlyMyFunc() = kotlin.io.println("클래스내부에서만 사용")

        constructor(){
            onlyMyFunc()
        }
    }

    class childClass : baseClass2(){

        //오버라이드
        override var name = ""
        override fun f1() = kotlin.io.println(this.toString() + " 재정의함.")
        fun f2() = kotlin.io.println("f2")

        // overloading
        fun f2(s : String ) = kotlin.io.println("f2:$s ")
        fun f2(s : String, num : Int ) = kotlin.io.println("f2: $s, $num ")

    }

    class Student (s : String){
        var name : String = s
        var nScore : Int = 0

        operator fun plus (student: Student) : Int {
            return student.nScore + this.nScore
        }

        //변화된 객체를 넘겨야 함
        operator fun inc() : Student {
            this.nScore++
            return this
        }

        fun printMe() = kotlin.io.println("${name}는 점수가 $nScore")
    }
}