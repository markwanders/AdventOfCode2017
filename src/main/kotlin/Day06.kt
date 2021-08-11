fun main() {
    val banks = input.split("\t").mapIndexed { index, s -> index to s.toInt() }.toMap().toMutableMap()
    val configurations = mutableListOf(banks.toString())
    var loop = ""
    var loopSize = 1
    while (true) {
        val max = banks.maxByOrNull { it.value }!!
        val distribute = max.value
        banks[max.key] = 0
        for (i in 1..distribute) {
            val bankNr = (i + max.key) % banks.size
            banks[bankNr] = banks[bankNr]!! + 1
        }
        val configuration = banks.toString()
        if(configuration in configurations && loop.isEmpty()) {
            println("Answer part 1: ${configurations.size}")
            loop = banks.toString()
        } else if (banks.toString() == loop) {
            println("Answer part 2: $loopSize")
            return
        } else {
            if (loop.isNotEmpty()) {
                loopSize += 1
            } else {
                configurations.add(configuration)
            }
        }
    }
}

private const val input = "4\t10\t4\t1\t8\t4\t9\t14\t5\t1\t14\t15\t0\t15\t3\t5"
private const val test = "0\t2\t7\t0"