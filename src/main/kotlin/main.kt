import Manager.ChainManager
import VO.SomeThingVO
import core.Block
import core.BlockChain
import core.HashUtil
import kotlinx.coroutines.runBlocking
import java.util.ArrayList

//    val testSomeThingVO = SomeThingVO("", 0, "", "")
//    val start = System.currentTimeMillis()
//    val testBlock = Block(0, null, testSomeThingVO, "aaaaa")
//    println(testBlock.hash)
//    val testBlock2 = Block(0, null, testSomeThingVO, "aaaaa")
//    println(testBlock2.hash)
//    val end = System.currentTimeMillis()
//    println("소요 시간 : ${end - start}ms")

//    val testSomeThingVO = SomeThingVO("", 0, "", "")
//    val start1 = System.currentTimeMillis()
//    val blockChain = BlockChain(ArrayList())
//    blockChain.add(testSomeThingVO)
//    blockChain.add(testSomeThingVO)
//    blockChain.toStringList()
//    val end2 = System.currentTimeMillis()
//
//    println("소요 시간 : ${end2 - start1}ms")

fun main(args: Array<String>) = runBlocking {

    val chain = ChainManager()
    chain.run()
    while (true) {
        println("1. add data")
        println("2. add node")
        println("3. remove node")
        println("4. list block chain")
        println("5. setDifficultyTarget")
        println("6. exit")
        print("cmd : ")
        val cmd = readLine()!!
        if (cmd.contains('1')) {
            print("to : ")
            val someTo = readLine()!!
            print("pay : ")
            val somePay = readLine()!!.toInt()
            print("from : ")
            val someFrom = readLine()!!
            print("memo : ")
            val someMemo = readLine()!!

            chain.addData(SomeThingVO(someTo, somePay, someFrom, someMemo))
        } else if (cmd.contains('2')) {
            chain.addNode()
        } else if (cmd.contains('3')) {
            print("select node : ")
            val select = readLine()!!.toInt()
            chain.removeNode(select)
        } else if (cmd.contains('4')) {
            println("-------- Block Chain --------")
            chain.showBlockChain()
            println("-------- Block Chain --------")
        } else if (cmd.contains('5')) {
            print("difficulty string : ")
            val difficulty = readLine()!!
            difficulty.toLowerCase()
            chain.setDifficultyTarget(difficulty)
        } else if (cmd.contains('6')) {
            break
        } else {
            break
        }
        println("----------------")
    }
}