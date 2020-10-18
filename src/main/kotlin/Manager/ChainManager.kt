package Manager

import VO.SomeThingVO
import core.Block
import core.BlockChain
import java.util.*
import kotlin.collections.ArrayList
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

class ChainManager() {

    private val networkParticipants = Vector<BlockChain>()

    private val mutex = Mutex()

    private fun syncCheckBlockChain() =
        CoroutineScope(Dispatchers.Default).launch {
            repeat(10) {
                delay(10000L) // 10 sec
                mutex.withLock {
                    for (elem_x in networkParticipants) {
                        for (elem_y in networkParticipants) {
                            if (elem_x.blockChain.size == elem_y.blockChain.size) {
                                if (elem_x.blockChain == elem_y.blockChain) {
                                    elem_x.dataHaveProblem = false
                                    elem_y.dataHaveProblem = false
                                }
                            } else {
                                elem_x.dataHaveProblem = true
                                elem_y.dataHaveProblem = true
                            }

                        }
                    }
                    var troubleFreeBlockChain: ArrayList<Block> = ArrayList()
                    for (elem in networkParticipants) {
                        if (!elem.dataHaveProblem) {
                            troubleFreeBlockChain = elem.blockChain
                            break
                        }
                    }
                    for (elem in networkParticipants) {
                        if (elem.dataHaveProblem) {
                            elem.blockChain = troubleFreeBlockChain
                        }
                    }
                }
//                println("SyncBlockChain!")
            }
        }

    private fun syncBlockChain(node:ArrayList<Block>) =
        CoroutineScope(Dispatchers.Default).launch {
            for(elem in networkParticipants) {
                elem.blockChain = node
            }
        }

    fun addData(data: SomeThingVO) = runBlocking {
        if(networkParticipants.isEmpty()) {
            println("need add Node!!")
            return@runBlocking
        }
        val node = networkParticipants[selectNode()]
        println("choice Node ID : ${node.id}")
        val time = measureTimeMillis {
            node.add(data)
            syncBlockChain(node.blockChain)
        }
        println("data add time : $time")
    }

    fun setDifficultyTarget(target: String) {
        for(elem in networkParticipants) {
            elem.setDifficultyTarget(target)
        }
    }

    private fun selectNode(): Int = rand(0, networkParticipants.size)

    fun addNode() {
        if(networkParticipants.isEmpty()) {
            networkParticipants.add(BlockChain(ArrayList()))
        }
        networkParticipants.add(BlockChain(networkParticipants.firstElement().blockChain))
    }

    fun removeNode(index:Int) {
        networkParticipants.removeElementAt(index)
    }

    fun showBlockChain() {
        networkParticipants.firstElement().toStringList()
    }

    fun run() {
        syncCheckBlockChain()
    }


    private val random = Random()
    private fun rand(from: Int, to: Int): Int {
        return random.nextInt(to - from) + from
    }
}