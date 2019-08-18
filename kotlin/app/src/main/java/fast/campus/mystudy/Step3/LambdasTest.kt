package fast.campus.mystudy.Step3

import fast.campus.mystudy.TestClass

//fun main(args: Array<String>) {

class LambdasTest (p : (Any) ->Unit ) : TestClass(p) {

    override fun doTest() {
        //1 람다식으로 정의한 함수형 변수
        var pFunc = { a : Int, b : Int -> a + b}
        println(pFunc(10,30))

        //고차 함수에 람다식을 넘기기
        fn1(pFunc) //소가로 삭제 가능
        fn2 { n, n2 -> n + n2}

        // 고차함수에 람다식과 값을 같이 넘기기
        fn3(
            { n, n2 -> n +n2 },
            100, 210
        )

        // 함수의 주소값을 직접대입시키는 방법
        fn4(::Add)

        // 함수원형의 포인터를 대입시키는 편법
        var funParameter = {a : Int -> Add(a)}
        fn4( funParameter )

        // 위의 코딩을 점점 더 단순화
        fn4( { a : Int -> Add(a)} )
        fn4 { a : Int -> Add(a)}
        fn4 { a -> Add(a) }
    }

    fun fn1(func : (Int, Int) -> Int ){
        func(10, 10).let { 결과값 -> kotlin.io.println("결과값은 $결과값 입니다") }
    }
    fun fn2(func : (Int, Int) -> Int ){
        kotlin.io.println("fn2 : " + func(10, 10))
    }
    fun fn3(func : (Int, Int) -> Int, a : Int, b : Int ){
        kotlin.io.println("fn3 : " + func(a, b))
    }
    fun fn4(func : (Int) -> Int ){
        kotlin.io.println("fn4 : " + func(1))
    }

    fun Add(i : Int) : Int {return i+100}


}

