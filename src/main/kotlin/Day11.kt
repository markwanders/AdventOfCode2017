import java.io.File
import kotlin.math.abs

fun main() {
    var x = 0; var y = 0
    var maxDistance = 0
    input.split(",").forEach {
        when (it) {
            "nw" -> {
                x -= 1; y += 1
            }
            "ne" -> {
                x += 1; y += 1
            }
            "sw" -> {
                x -= 1; y -= 1
            }
            "se" -> {
                x += 1; y -= 1
            }
            "s" -> y -= 2
            "n" -> y += 2
        }
        if (distance(x, y) > maxDistance) {
            maxDistance = distance(x, y)
        }
    }
    println(distance(x, y))
    println(maxDistance)
}

fun distance(x: Int, y: Int): Int {
    return (abs(x) + abs(y)) / 2
}

private val input = File("src/main/resources/day11.txt").readText()