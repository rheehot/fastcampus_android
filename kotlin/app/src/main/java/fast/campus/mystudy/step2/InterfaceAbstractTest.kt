package fast.campus.mystudy.step2

import fast.campus.mystudy.TestClass

class InterfaceAbstractTest (p : (Any) -> Unit) : TestClass (p){

    override fun doTest() {
        // 구현하고 설정하는 곳
        var WhatYouWant = 2
        var inter : InterTest = if(WhatYouWant == 1) InterImp2() else InterImp()

        // 넘겨진 값을 신경안쓰고 사용하는 곳
        interfaceTest(inter)


        // 추상화
        var obj2 = TestAbstractImp();
        obj2.TestFunc()
        obj2.abstractFunc()

        // static
        kotlin.io.println(TestAbstractImp.staticVar);
        TestAbstractImp.staticFunc()
    }
}

fun interfaceTest( pInter : InterTest){
    pInter.TestFunc()
}

interface InterTest{
    fun TestFunc()
}

class InterImp : InterTest{
    override fun TestFunc() = println("InterImp  InterTest")
}

class InterImp2 : InterTest{
    override fun TestFunc() = println("InterImp  InterTest2")
}

// 반제품 개념
abstract class TestAbstract{
    fun TestFunc() = println("abstract TestFunc")
    abstract fun abstractFunc()
}

//반드시 구현해야 한다
class TestAbstractImp : TestAbstract(){

    override fun abstractFunc() = println("TestAbstractImp abstractFunc")
    // companion object {} 안에서 구현해야 static 가능
    companion object {
        var staticVar = "companion staticVar"
        fun staticFunc() = println("companion staticFunc")
    }
}