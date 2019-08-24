package fast.campus.mystudy.Step3

//class multiReturnTest {
//}

fun main (args : Array <String>){

    // 1. Extract
    // () 안에 변수를 나열한다
    // 일반적으로 data class, list, pair collection 의 반환값
    // 순서대로 특정범위까지 가져온다.

    val (v1, v2, v3 ) = listOf("Kotlin", 32, 123.0f)
    println ("${v1}, ${v2}, ${v3}")

    // 2 List 파티션으로 나누기
    val lst = (0..10).map { Student("name -${it}", (it % 3) + 1 ) }
    println (lst)

    val (l1, l2) = lst.partition { it.grade == 2 }
    println ("l1")

    l1.map { println (it) }
    println ("l2")

    l2.map { println (it) }

    // 3. 순위별로 가져오기
    val (n1, n2, n3) = (0..100).filter { it % 2 == 0 }.sortedByDescending { it }
    println (n1.toString() + ", " + n2.toString() + ", " + n3.toString())

}

data class Student (var name : String, var grade : Int)