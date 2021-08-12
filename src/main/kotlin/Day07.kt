import java.io.File

var contains = mapOf<String, List<String>>()
var weightPerProgram = mapOf<String, Int>()

fun main() {
    val towers = mutableListOf<List<String>>()
    val weights = mutableListOf<Int>()
    input.forEach {
        towers.add(Regex("[^A-Za-z,)]").replace(it, "")
            .replace(")", ",")
            .split(",")
            .filter { it.isNotBlank() })
        weights.add(Regex("[^0-9]").replace(it, "").toInt())
    }
    contains = towers.associate { tower ->
        tower[0] to tower.subList(1, tower.size)
    }
    val contained = contains.values.toList().flatten()
    val bottom = contains.keys.first { it !in contained }
    println("Answer part 1: $bottom")
    weightPerProgram = towers.map { it.first() }.zip(weights).toMap()

    totalWeight(bottom)
}

fun totalWeight(program: String): Int {
    val subTowers = contains[program]!!.map { totalWeight(it) }
    val weightOccurrences = subTowers.groupingBy { it }.eachCount()
    if (weightOccurrences.size > 1) {
            val indexOdd = subTowers.indexOf(weightOccurrences.keys.first { weightOccurrences[it] == 1 })
            val wrongWeight = contains[program]!!.map { weightPerProgram[it] }[indexOdd]
            println("Answer part 2: ${wrongWeight!! + (weightOccurrences.keys.first() - weightOccurrences.keys.last())}")
            return weightPerProgram[program]!! + subTowers.sum() + (weightOccurrences.keys.first() - weightOccurrences.keys.last())
    }
    return weightPerProgram[program]!! + subTowers.sum()
}


private val input = File("src/main/resources/day07.txt").readLines()
private val test = File("src/main/resources/day07_test.txt").readLines()