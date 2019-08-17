package fast.campus.mystudy.Step1

import fast.campus.mystudy.TestClass
import java.util.*

class StringTest (p : (Any) ->Unit ) : TestClass(p) {

    override fun doTest() {
        var sName = "박모씨"
        println(sName + " - The Gamer")

        var sLines = """

        1
        2
        3
        4
        5
        6
        7
        8
        9
        """
        println(sLines)

        // 3. 문자열 내의 포멧팅 방법
        // linux의 bash와 비슷함.

        var sForamatter = "$sName <-- sName의 값"
        println(sForamatter)

        // 4. linux bash와 비슷하니
        // ${}안에
        // 함수호출이나 수식처리도 가능함.
        var sBash = "${ "지금시각은  - " + Date()}" // Java class도 가져욜 수 있음.
        println(sBash)
    }


}