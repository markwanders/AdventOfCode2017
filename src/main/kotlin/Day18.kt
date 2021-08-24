import java.io.File
import kotlin.math.abs

var counter = 0

fun main() {
    val registers = mutableMapOf<String, Long>()
    var played = 0L
    var i = 0
    while (true) {
        val instruction = input[i].split(" ")
        val operation = instruction.first()
        val register = instruction[1]
        val registerValue = registers.getOrDefault(register, 0)
        val value = if (instruction.size > 2) {
            if (instruction[2].last().isDigit()) {
                instruction[2].toLong()
            } else {
                registers.getOrDefault(instruction[2], 0)
            }
        } else null
        when (operation) {
            "snd" -> played = registerValue
            "set" -> registers[register] = value!!
            "add" -> registers[register] = registerValue + value!!
            "mul" -> registers[register] = registerValue * value!!
            "mod" -> registers[register] = registerValue % value!!
            "jgz" -> if (registerValue > 0) i += (value!!.toInt() - 1)
            "rcv" -> {
                if (registerValue > 0) break
            }
        }
        i += 1
    }
    println(played)
    val queues = mapOf(0 to mutableListOf(), 1 to mutableListOf<Long>())
    var pointer0 = 0
    var pointer1 = 0
    val registers0 = mutableMapOf("p" to 0L)
    val registers1 = mutableMapOf("p" to 1L)
    do {
        val old0 = pointer0
        val old1 = pointer1
        pointer0 = compute(pointer0, registers0, 0, queues)
        pointer1 = compute(pointer1, registers1, 1, queues)
    } while (!(old0 == pointer0 && old1 == pointer1))
    println(counter)
}

fun compute(
    pointer: Int,
    registers: MutableMap<String, Long>,
    programId: Int,
    queues: Map<Int, MutableList<Long>>
): Int {
    val instruction = input[pointer].split(" ")
    val operation = instruction.first()
    val register = instruction[1]
    val values = instruction.drop(1).map {
        if (it.last().isDigit()) {
            it.toLong()
        } else {
            registers.getOrDefault(it, 0)
        }
    }
    when (operation) {
        "snd" -> {
            queues[abs(programId - 1)]!!.add(values.first())
            if (programId == 1) {
                counter += 1
            }
        }
        "set" -> registers[register] = values.last()
        "add" -> registers[register] = values.first() + values.last()
        "mul" -> registers[register] = values.first() * values.last()
        "mod" -> registers[register] = values.first() % values.last()
        "jgz" -> if (values.first() > 0) return pointer + values.last().toInt()
        "rcv" -> if (queues[programId]!!.isEmpty()) {
            return pointer
        } else {
            registers[register] = queues[programId]!!.removeAt(0)
        }
    }
    return pointer + 1
}

private val input = File("src/main/resources/day18.txt").readLines()