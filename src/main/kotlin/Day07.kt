import java.io.File

fun main() {
    val towers = input.map {
        Regex("[^A-Za-z,)]").replace(it, "")
            .replace(")", ",")
            .split(",")
            .filter { it.isNotBlank() }
    }
    val contains = towers.associate { tower ->
        tower[0] to tower.subList(1, tower.size)
    }
    val contained = contains.values.toList().flatten()
    println(contains.keys.filter { it !in contained })
}

private val input = File("src/main/resources/day07.txt").readLines()