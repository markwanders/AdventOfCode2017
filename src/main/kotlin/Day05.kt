import java.io.File

fun main() {
    jump(1, input.toMutableList())
    jump(2, input.toMutableList())
}

fun jump(part: Int, instructions: MutableList<Int>) {
    var i = 0
    var steps = 0
    while (i < instructions.size) {
        val old = instructions[i]
        instructions[i] = instructions[i] + 1
        if (part == 2 && old >= 3) {
            instructions[i] = instructions[i] - 2
        }
        i += old
        steps += 1
    }
    println(steps)
}

private val input = File("src/main/resources/day05.txt").readLines().map { it.toInt() }
