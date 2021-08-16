import java.util.*

fun  main() {
    val list = (0..255)
    val part1 = knot(list.toMutableList(), ints, 1)
    println("${part1[0] * part1[1]}")
    val sparse = knot(list.toMutableList(), ascii, 64)
    val dense = sparse.chunked(16).map { block -> block.reduce{sum, element -> sum.xor(element)}}
    println(dense.joinToString("") { Integer.toHexString(it).padStart(2, '0') })
}

fun knot(list: MutableList<Int>, lengths: List<Int>, rounds: Int) : List<Int> {
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


private const val input = "18,1,0,161,255,137,254,252,14,95,165,33,181,168,2,188"
private val ints = input.split(",").map { it.toInt() }
private val ascii: List<Int> = input.map { it.code }.toMutableList() + ("17, 31, 73, 47, 23".split(", ").map { it.toInt() })