import java.io.File

fun main () {
    var sum = 0
    (0..input.keys.maxOrNull()!!).forEach { i ->
        if (i in input.keys) {
            run {
                if (i % (2 * input[i]!! - 2)  == 0) {
                    sum += i * input[i]!!
                }
            }
        }
    }
    println(sum)
}

private val input = File("src/main/resources/day13.txt")
    .readLines()
    .associate { it.split(": ").first().toInt() to  it.split(": ").last().toInt()}
    .toMap()