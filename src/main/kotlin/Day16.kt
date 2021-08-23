import java.io.File

var programs = "abcdefghijklmnop".toCharArray()

fun main() {
    dance()
    println(programs)
    val seen = mutableSetOf<String>()
    while (programs.joinToString("") !in seen) {
        seen.add(programs.joinToString(""))
        dance()
    }
    println(seen.toList()[999999999%seen.size])
}


fun dance() {
    input.split(",").forEach { move ->
        when {
            move.startsWith('s') -> {
                val spin = move.drop(1).toInt()
                programs = (programs.takeLast(spin) + programs.take(programs.size - spin)).toCharArray()
            }
            move.startsWith('x') -> {
                val exchange = move.drop(1).split("/").map { it.toInt() }
                val a =  programs[exchange.first()]
                val b = programs[exchange.last()]
                programs[exchange.first()] = b
                programs[exchange.last()] = a
            }
            move.startsWith('p') -> {
                val partner = move.drop(1).split('/').map { it.single() }
                val a = programs.indexOf(partner.first())
                val b = programs.indexOf(partner.last())
                programs[a] = partner.last()
                programs[b] = partner.first()
            }
        }
    }
}

private val input = File("src/main/resources/day16.txt").readText()