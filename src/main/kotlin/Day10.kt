import java.util.*

fun  main() {
    val list = (0..255).toMutableList()
    var skip = 0
    var position = 0
    input.forEach {
        Collections.rotate(list, -position)
        list.subList(0, it).reverse()
        Collections.rotate(list, position)
        position += skip + it
        position %= list.size
        skip += 1
    }
    println("${list[0] * list[1]}")
}

private val input = "18,1,0,161,255,137,254,252,14,95,165,33,181,168,2,188".split(",").map { it.toInt() }