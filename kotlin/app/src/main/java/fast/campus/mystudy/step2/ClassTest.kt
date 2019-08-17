package fast.campus.mystudy.step2

import fast.campus.mystudy.TestClass

//fun main(args: Array<String>) {
class ClassTest (p : (Any) -> Unit) : TestClass (p){

    override fun doTest() {
        Test1().showInfo()

        Test1("PSW", 50).showInfo()

        Test2("나다").showInfo()
        Test2("PSW", 50).showInfo()
    }

    class Test1 {

        var sName : String = "Test1"
        var nAge : Int = 10

        constructor(){
            kotlin.io.println(this.toString())
        }

        constructor(name : String, age : Int){
            sName = name
            nAge = age
        }

        fun showInfo () = kotlin.io.println("${sName} : ${nAge}")
    }

    class Test2(name : String){

        var sName : String = "Test2"
        var nAge : Int = 10

        init {
            sName = name
        }

        constructor(name : String, age : Int) : this(name){
            sName = name
            nAge = age
        }

        fun showInfo () = kotlin.io.println("${sName} : ${nAge}")
    }

    open class ParentClass(msg : String) {
        var message = msg
        fun sayHello() = kotlin.io.println(message)
    }

    class ChildClass(m : String ) : ParentClass( m ){

    }

}