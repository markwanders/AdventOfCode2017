import java.io.File

fun main() {
    val registers = mutableMapOf<String, Int>()
    var max = 0
    input.forEach {
        val parts = it.split(" ")
        val register = parts[0]
        val operation = parts[1]
        val compareRegister = parts[4]
        val comparison = parts[5]
        val compareValue = parts[6]
        val execute: Boolean = when (comparison) {
            "==" -> registers.getOrDefault(compareRegister, 0) == compareValue.toInt()
            "!=" -> registers.getOrDefault(compareRegister, 0) != compareValue.toInt()
            ">" -> registers.getOrDefault(compareRegister, 0) > compareValue.toInt()
            ">=" -> registers.getOrDefault(compareRegister, 0) >= compareValue.toInt()
            "<" -> registers.getOrDefault(compareRegister, 0) < compareValue.toInt()
            "<=" -> registers.getOrDefault(compareRegister, 0) <= compareValue.toInt()
            else -> {
                println("Unknown comparison operator $comparison")
                false
            }
        }
        if (execute) {
            val value = if (operation == "inc") parts[2].toInt() else -parts[2].toInt()
            registers[register] = registers.getOrDefault(register, 0) + value
            if (registers[register]!! > max) {
                max = registers[register]!!
            }
        }
    }
    println("Answer part 1: ${registers.values.maxOrNull()}")
    println("Answer part 2: $max")
}

private val input = File("src/main/resources/day08.txt").readLines()
