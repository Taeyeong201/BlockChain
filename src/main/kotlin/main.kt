import VO.SomeThingVO
import core.Block
import core.BlockChain
import core.HashUtil
import java.util.ArrayList

//    val blockChain = BlockChain(ArrayList())
//    blockChain.add(testSomeThingVO)
//    blockChain.add(testSomeThingVO)
//    blockChain.toStringList()



fun main(args: Array<String>) {
    val testSomeThingVO = SomeThingVO("", 0, "", "")
    val start = System.currentTimeMillis()
    val testBlock = Block(0, null, testSomeThingVO, "aaaaa")
    println(testBlock.hash)
    val testBlock2 = Block(0, null, testSomeThingVO, "aaaaa")
    println(testBlock2.hash)
    val end = System.currentTimeMillis()
    println("소요 시간 : ${end - start}ms")

    while(true) {
        print("to : ")
        val someTo = readLine()!!
        print("pay : ")
        val somePay = readLine()!!.toInt()
        print("from : ")
        val someFrom = readLine()!!
        print("memo : ")
        var someMemo = readLine()


        print("exit?(y) ")
        val isExit = readLine()!!
        if(isExit.contains("y")) {
            break
        }
    }
}