package core

import VO.SomeThingVO

data class Block(
        val index: Int,
        val prevBlockHash: String?,
        val transactions: SomeThingVO,
        val difficultyTarget:String,
        val timestamp: Long = System.currentTimeMillis()
) {
        var hash: String = calculatedHash()
        var nonce: Int = 0
        init {
                hash = mineBlock(difficultyTarget)
        }

        private fun calculatedHash(): String {
                val input: String =
                        prevBlockHash + transactions.toString() + timestamp.toString() + nonce.toLong()
                return HashUtil.applySHA256(input)
        }

        fun mineBlock(difficulty: String) : String {
                while (hash.substring(0, difficulty.length) != difficulty) {
                        nonce++
                        hash = calculatedHash()
                }
                println("Success mining.")
                return hash
        }

        override fun toString(): String {
                return "{\n" +
                        "\tindex : ${index}\n" +
                        "\tdata : ${transactions}\n" +
                        "\tprev hash : ${prevBlockHash}\n" +
                        "\thash : ${hash}\n" +
                        "\tnonce : ${nonce}\n" +
                        "\tdifficultyTarget : ${difficultyTarget}\n" +
                        "\ttimestamp : ${timestamp}\n" +
                        "}\n"
        }
}