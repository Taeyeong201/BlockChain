package core

import VO.SomeThingVO


class BlockChain(var blockChain: ArrayList<Block>) {
    val id: Int = makeId++
    private var difficultyTarget:String = "0000"

    var dataHaveProblem = false

    fun setDifficultyTarget(target: String) {
        difficultyTarget = target
    }

    suspend fun add(data: SomeThingVO) {
        if (blockChain.isEmpty()) {
            blockChain.add(Block(1, "0", data, difficultyTarget))
            println("Genesis Block Create!")
            return
        }
        val block:Block = Block(blockChain.size + 1, lastBlock().hash, data, difficultyTarget)
        blockChain.add(block)
    }

    fun lastBlock(): Block {
        return blockChain.last()
    }

    fun toStringList() {
        for(block in blockChain) {
            println(block)
        }
    }

    companion object {
        var makeId: Int = 1
    }
}