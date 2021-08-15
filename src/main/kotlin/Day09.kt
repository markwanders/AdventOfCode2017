import java.io.File

fun main() {
    var i = 0
    var totalGroupLevel = 0
    var garbage = false
    var groupLevel = 0
    var garbageChars = 0
    do {
        when {
            input[i] == '!' -> input.removeAt(i + 1)
            input[i] == '<' && !garbage -> garbage = true
            input[i] == '>' && garbage -> garbage = false
            input[i] == '{' && !garbage -> groupLevel += 1
            input[i] == '}' && !garbage && groupLevel > 0 -> {
                totalGroupLevel += groupLevel
                groupLevel -= 1
            }
            garbage -> garbageChars += 1
        }
        i += 1
    } while (i < input.size)
    println("Answer part 1: $totalGroupLevel")
    println("Answer part 2: $garbageChars")
}

private val input = File("src/main/resources/day09.txt").readText().toMutableList()
