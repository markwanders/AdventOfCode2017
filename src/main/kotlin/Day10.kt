import java.util.*

fun main() {
    val part1 = knot(ints, 1)
    println("${part1[0] * part1[1]}")
    println(hex(ascii, 64))
}

fun knot(lengths: List<Int>, rounds: Int): List<Int> {
    val list = (0..255).toMutableList()
    var skip = 0
    var position = 0
    repeat(rounds) {
        lengths.forEach {
            Collections.rotate(list, -position)
            list.subList(0, it).reverse()
            Collections.rotate(list, position)
            position += skip + it
            position %= list.size
            skip += 1
        }
    }
    return list
}

fun dense(lengths: List<Int>, rounds: Int): List<Int> {
    return knot(lengths, rounds).chunked(16).map { block -> block.reduce { sum, element -> sum.xor(element) } }
}

fun hex(lengths: List<Int>, rounds: Int): String {
    return dense(
        lengths + ("17, 31, 73, 47, 23".split(", ").map { it.toInt() }),
        rounds
    ).joinToString("") { Integer.toHexString(it).padStart(2, '0') }
}

private const val input = "18,1,0,161,255,137,254,252,14,95,165,33,181,168,2,188"
private val ints = input.split(",").map { it.toInt() }
private val ascii: List<Int> = input.map { it.code }.toMutableList()