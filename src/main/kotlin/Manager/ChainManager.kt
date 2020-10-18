package Manager

import VO.SomeThingVO
import core.Block
import core.BlockChain
import java.util.*
import kotlin.collections.ArrayList
import kotlinx.coroutines.*

class ChainManager() {
    val networkParticipants = Vector<BlockChain>()
    private var isClose = false

    private val syncBlockChain: Thread = Thread {
        while(!isClose) {
            Thread.sleep(20000) // 20 sec
            for(elem_x in networkParticipants) {
                for (elem_y in networkParticipants) {
                    if(elem_x.blockChain.size == elem_y.blockChain.size) {
                        if(elem_x.blockChain == elem_y.blockChain) {
                            elem_x.dataHaveProblem = false
                            elem_y.dataHaveProblem = false
                        }
                    }
                    else {
                        elem_x.dataHaveProblem = true
                        elem_y.dataHaveProblem = true
                    }

                }
            }
            var troubleFreeBlockChain: ArrayList<Block> = ArrayList()
            for(elem in networkParticipants) {
                if(!elem.dataHaveProblem) {
                    troubleFreeBlockChain = elem.blockChain
                    break
                }
            }
            for(elem in networkParticipants) {
                if(elem.dataHaveProblem) {
                    elem.blockChain = troubleFreeBlockChain
                }
            }

        }
    }
    suspend fun syncBlockChainCoroutine() = runBlocking<Unit> {
        while(!isClose) {
            Thread.sleep(20000) // 20 sec
            for(elem_x in networkParticipants) {
                for (elem_y in networkParticipants) {
                    if(elem_x.blockChain.size == elem_y.blockChain.size) {
                        if(elem_x.blockChain == elem_y.blockChain) {
                            elem_x.dataHaveProblem = false
                            elem_y.dataHaveProblem = false
                        }
                    }
                    else {
                        elem_x.dataHaveProblem = true
                        elem_y.dataHaveProblem = true
                    }

                }
            }
            var troubleFreeBlockChain: ArrayList<Block> = ArrayList()
            for(elem in networkParticipants) {
                if(!elem.dataHaveProblem) {
                    troubleFreeBlockChain = elem.blockChain
                    break
                }
            }
            for(elem in networkParticipants) {
                if(elem.dataHaveProblem) {
                    elem.blockChain = troubleFreeBlockChain
                }
            }

        }
    }

    init {
        syncBlockChain.start()
    }

    fun addData(data: SomeThingVO) {

    }

    fun addNode() {

    }

    fun removeNode() {

    }

    fun run() {

    }

    fun close() {
        isClose = true
    }

}