import java.util.*

fun main() {
    val input = 344
    val buffer = mutableListOf(0)
    repeat(2017) {
        Collections.rotate(buffer, -input)
        buffer.add(buffer.size)
    }
    println(buffer.first())

    var position = 0
    var answer = 0
    (1..50000000).forEach {
        position = (position + input)%it + 1
        if (position == 1) {
            answer = it
        }
    }
    println(answer)
}