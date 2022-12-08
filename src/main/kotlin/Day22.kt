import java.io.File

private val input = File("src/main/resources/day22.txt").readLines()
private val map = mutableMapOf<Pair<Int, Int>, Char>().withDefault { '.' }

fun main() {
    input.forEachIndexed { y, s -> s.forEachIndexed { x, c -> map[y to x] = c } }
    var position = input.size/2 to input.size/2
    var facing = -1 to 0
    var infected = 0
    repeat(10000) {
        when (map.getValue(position)) {
            '#' -> {
                map[position] = '.'
                facing = turnRight(facing)
            }
            '.' -> {
                map[position] = '#'
                facing = turnLeft(facing)
                infected++
            }
        }
        position = position.first + facing.first to position.second + facing.second
    }
    println(infected)

    map.clear()
    input.forEachIndexed { y, s -> s.forEachIndexed { x, c -> map[y to x] = c } }
    position = input.size/2 to input.size/2
    facing = -1 to 0
    infected = 0
    repeat(10000000) {
        when (map.getValue(position)) {
            '#' -> {
                map[position] = 'f'
                facing = turnRight(facing)
            }
            '.' -> {
                map[position] = 'w'
                facing = turnLeft(facing)
            }
            'w' -> {
                map[position] = '#'
                infected++
            }
            'f' -> {
                map[position] = '.'
                facing = reverse(facing)
            }
        }
        position = position.first + facing.first to position.second + facing.second
    }
    println(infected)
}

fun turnLeft(a: Pair<Int, Int>): Pair<Int, Int> {
    if (a.first == -1) {
        return 0 to -1
    }
    if (a.first == 1) {
        return 0 to 1
    }
    if (a.second == -1) {
        return 1 to 0
    }
    if (a.second == 1) {
        return -1 to 0
    }
    return 0 to 0
}

fun turnRight(a: Pair<Int, Int>): Pair<Int, Int> {
    if (a.first == -1) {
        return 0 to 1
    }
    if (a.first == 1) {
        return 0 to -1
    }
    if (a.second == -1) {
        return -1 to 0
    }
    if (a.second == 1) {
        return 1 to 0
    }
    return 0 to 0
}

fun reverse(a: Pair<Int, Int>): Pair<Int, Int> = -a.first to -a.second
