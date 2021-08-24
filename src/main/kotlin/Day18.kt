import java.io.File

fun main() {
    val registers = mutableMapOf<String, Long>()
    var i = 0
    var played = 0L
    while(true) {
        val instruction = input[i].split(" ")
        val operation = instruction.first()
        val register = instruction[1]
        val registerValue = registers.getOrDefault(register, 0)
        val value = if(instruction.size > 2) {
            if(instruction[2].last().isDigit()) {
                instruction[2].toLong()
            } else {
                registers.getOrDefault(instruction[2], 0)
            }
        } else null
        when(operation) {
            "snd" -> played = registerValue
            "set" -> registers[register] = value!!
            "add" -> registers[register] = registerValue + value!!
            "mul" -> registers[register] = registerValue * value!!
            "mod" -> registers[register] = registerValue % value!!
            "jgz" -> if(registerValue > 0) i += (value!!.toInt() - 1)
            "rcv" -> {
                if(registerValue > 0) break
            }
        }
        i+=1
    }
    println(played)
}

private val input = File("src/main/resources/day18.txt").readLines()