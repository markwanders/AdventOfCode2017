import java.util.*

fun main() {
    val input = 344
    val buffer = mutableListOf(0)
    repeat(2017) {
        Collections.rotate(buffer, -input)
        buffer.add(buffer.size)
    }
    println(buffer.first())

}