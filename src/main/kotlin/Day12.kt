import java.io.File

fun main() {
    val connections = mutableMapOf<Int, List<Int>>()
    input.forEach { line ->
        val strings = line.split(" ")
            .map { it.replace(",", "") }
            .toMutableList()
        strings.removeAt(1)
        val numbers = strings.map { it.toInt() }
        connections[numbers.first()] = numbers.drop(1)
    }

    println(findGroup(0, connections).size)

    var groups = 0
    while (connections.isNotEmpty()) {
        findGroup(connections.keys.first(), connections).forEach { connections.remove(it) }
        groups += 1
    }
    println(groups)
}


fun findGroup(value: Int, connections: MutableMap<Int, List<Int>>): MutableSet<Int> {
    val programs = mutableSetOf<Int>()
    val queue = mutableListOf(value)
    while (queue.isNotEmpty()) {
        val number = queue.removeFirst()
        connections[number]!!.forEach {
            if (it !in queue && it !in programs) {
                queue.add(it)
                programs.add(it)
            }
        }
    }
    return programs
}

private val input = File("src/main/resources/day12.txt").readLines()