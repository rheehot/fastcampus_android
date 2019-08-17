package fast.campus.mystudy.Step1

fun main(args: Array<String>) {

}

class Test1 {

    var sName : String = "Test1"
    var nAge : Int = 10

    constructor(){
        println(this.toString())
    }

    constructor(name : String, age : Int){
        sName = name
        nAge = age
    }

    fun showInfo () = println ("${sName} : ${nAge}")
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

    fun showInfo () = println ("${sName} : ${nAge}")
}

open class ParentClass(msg : String) {
    var message = msg
    fun sayHello() = println(message)
}

class ChildClass(m : String ) : ParentClass( m ){
}