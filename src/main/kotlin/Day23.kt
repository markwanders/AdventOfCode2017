import java.io.File

private val input = File("src/main/resources/day23.txt").readLines()

fun main() {
    val registers = ('a'..'h').associate { it.toString() to 0 }.toMutableMap()
    println(registers)
    var pointer = 0
    var mulCounter = 0
    while (pointer in input.indices) {
        val instruction = input[pointer].split(" ")
        val (o, x, yy) = instruction
        val y = if (yy in registers.keys) registers[yy]!! else yy.toInt()
        when (o) {
            "set" -> {
                registers[x] = y
                pointer++
            }
            "sub" -> {
                registers[x] = registers[x]!! - y
                pointer++
            }

            "mul" -> {
                registers[x] = registers[x]!! * y
                pointer++
                mulCounter++
            }
            "jnz" -> {
                if (registers[x] != 0 || (x !in registers.keys && x.toInt() != 0)) {
                    pointer += y
                } else {
                    pointer++
                }
            }
        }
    }
    println(mulCounter)
}