package fast.campus.mystudy.Step3

import fast.campus.mystudy.TestClass

class infixTest (p : (Any) -> Unit) : TestClass(p){

    //    수학에서 infix는 중위표기법으로 사칙연산자(+. -. *, /)와 같이 좌측과 우측의 값이 존재하는 연산자를 말한다.
//    . 연산자를 대처하는 메소드 표기법
//    좌측은 객체 우측은 1개의 값
    override fun doTest() {
        // 삶은 이런 것!!
        var MyCompany = RankMyCompany()
        MyCompany AddPoint "일이좋다"
        MyCompany AddPoint "자부심"
        MyCompany AddPoint "높은연봉"
        MyCompany AddPoint "유명회사"
        MyCompany AddPoint "야근당연"

        println(MyCompany.Rank)
    }

    //fun main (args : Array <String>){

    class RankMyCompany {
        val m = mapOf<String, Int>(
            "일이좋다"   to 25,
            "자부심"     to 30,
            "높은연봉"   to 25,
            "유명회사"   to 20,
            "야근당연"   to -60)

        var Rank : Int = 0

        // infix는 확장함수나 멤버함수로 구현되어야 한다.
        infix fun AddPoint(s : String) : Int{
            this.Rank += m[s]!!
            return this.Rank
        }
    }
}

