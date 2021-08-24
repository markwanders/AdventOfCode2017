import java.io.File

fun main() {
    var y = 0
    var x = input[0].indexOf('|')
    var direction = "d"
    val letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val seen = mutableListOf<Char>()
    var steps = 1
    while (true) {
        steps += 1
        val char = get(y, x)
        if (char in letters) {
            seen.add(char)
        }
        when (direction) {
            "d" -> {
                if ((char == '+' || char in letters) && get(y + 1, x) == ' ') {
                    if (get(y, x + 1) != ' ') {
                        direction = "r"
                        x += 1
                    } else if (get(y, x - 1) != ' ') {
                        direction = "l"
                        x -= 1
                    } else {
                        break
                    }
                } else {
                    y += 1
                }
            }
            "u" -> {
                if ((char == '+' || char in letters) && get(y - 1, x) == ' ') {
                    if (get(y, x + 1) != ' ') {
                        direction = "r"
                        x += 1
                    } else if (get(y, x - 1) != ' ') {
                        direction = "l"
                        x -= 1
                    } else {
                        break
                    }
                } else {
                    y -= 1
                }
            }
            "l" -> {
                if ((char == '+' || char in letters) && get(y, x - 1) == ' ') {
                    if (get(y - 1, x) != ' ') {
                        direction = "u"
                        y -= 1
                    } else if (get(y + 1, x) != ' ') {
                        direction = "d"
                        y += 1
                    } else {
                        break
                    }
                } else {
                    x -= 1
                }
            }
            "r" -> if ((char == '+' || char in letters) && get(y, x + 1) == ' ') {
                if (get(y - 1, x) != ' ') {
                    direction = "u"
                    y -= 1
                } else if (get(y + 1, x) != ' ') {
                    direction = "d"
                    y += 1
                } else {
                    break
                }
            } else {
                x += 1
            }
        }
    }
    println(seen.joinToString(""))
    println(steps)
}

fun get(y: Int, x: Int) : Char {
    return (input.getOrElse(y + 1) {" ".toCharArray()}).getOrElse(x) { ' ' }
}

private val input = File("src/main/resources/day19.txt").readLines().map { it.toCharArray() }