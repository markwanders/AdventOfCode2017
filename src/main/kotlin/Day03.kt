import kotlin.math.abs

fun main() {
    val data = 277678
    var point = Pair(0, 0)
    var deltaX = 0
    var deltaY = 0
    val memory = hashMapOf<Pair<Int, Int>, Int>()
    for (i in 1..data) {
        if (memory.isNotEmpty()) {
            if (deltaX == 0 && deltaY == 0) {
                deltaX = 1
            } else if (deltaX == 1 && !memory.containsKey(Pair(point.first, point.second + 1))) {
                deltaX = 0
                deltaY = 1
            } else if (deltaY == 1 && !memory.containsKey(Pair(point.first - 1, point.second))) {
                deltaX = -1
                deltaY = 0
            } else if (deltaX == -1 && !memory.containsKey(Pair(point.first, point.second - 1))) {
                deltaX = 0
                deltaY = -1
            } else if (deltaY == -1 && !memory.containsKey(Pair(point.first + 1, point.second))) {
                deltaX = 1
                deltaY = 0
            }
        }
        point = Pair(point.first + deltaX, point.second + deltaY)
        memory[point] = i
    }
    println(manhattan(point))
}

fun manhattan(coordinates: Pair<Int, Int>) : Int {
    return abs(coordinates.first) + abs(coordinates.second)
}