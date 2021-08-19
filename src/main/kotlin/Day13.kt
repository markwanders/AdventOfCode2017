import java.io.File

fun main() {
    println(input.keys.filter { it % (2 * input[it]!! - 2) == 0 }.sumOf { it * input[it]!! })

    var delay = 0
    do {
        delay += 1
    } while (input.keys.any { (delay + it) % (2 * input[it]!! - 2) == 0 })
    println(delay)
}

private val input = File("src/main/resources/day13.txt")
    .readLines()
    .associate { it.split(": ").first().toInt() to it.split(": ").last().toInt() }
    .toMap()