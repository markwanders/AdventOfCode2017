import java.io.File

fun main() {
    var sum = 0
    input.keys.forEach { i ->
        if (i % (2 * input[i]!! - 2) == 0) {
            sum += i * input[i]!!
        }
    }
    println(sum)

    var delay = 0
    do {
        var crossed = true
        delay += 1
        input.keys.forEach { i ->
            if ((delay + i) % (2 * input[i]!! - 2) == 0) {
                crossed = false
            }
        }
    } while (!crossed)
    println(delay)
}

private val input = File("src/main/resources/day13.txt")
    .readLines()
    .associate { it.split(": ").first().toInt() to it.split(": ").last().toInt() }
    .toMap()