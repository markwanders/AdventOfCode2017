import kotlin.math.abs

fun main() {
    val data = 277678
    var point = Pair(0, 0)
    var deltaX = 0
    var deltaY = 0
    val memory = hashMapOf<Pair<Int, Int>, Int>()
    val memoryPartTwo = hashMapOf<Pair<Int, Int>, Int>()
    var memoryPartTwoMax = 1
    val offsets = listOf(Pair(0,1), Pair(1,1), Pair(1,0), Pair(1,-1), Pair(-1,1), Pair(-1, -1), Pair(0,-1), Pair(-1, 0))
    memoryPartTwo[Pair(0,0)] = 1
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
        if (memoryPartTwoMax < data && !memoryPartTwo.containsKey(point)) {
            memoryPartTwo[point] = offsets.sumOf { offset ->
                memoryPartTwo.getOrElse(Pair(point.first + offset.first, point.second + offset.second)) { 0 }
            }
            if (memoryPartTwo[point]!! > memoryPartTwoMax) {
                memoryPartTwoMax = memoryPartTwo[point]!!
            }
        }
    }
    println(manhattan(point))
    println(memoryPartTwo.maxByOrNull { it.value }!!.value)
}

fun manhattan(coordinates: Pair<Int, Int>) : Int {
    return abs(coordinates.first) + abs(coordinates.second)
}