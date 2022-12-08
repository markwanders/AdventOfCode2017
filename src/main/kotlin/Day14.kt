private val seen = mutableSetOf<Pair<Int, Int>>()
private val grid = mutableListOf<String>()

fun main() {
    println("Answer part 1: " + (0..127).sumOf { rowNumber ->
        hex("$input-$rowNumber".map { it.code }, 64) //knot hash the int value of the string
            .map { Integer.toBinaryString(it.toString().toInt(radix = 16)).padStart(4, '0') } //convert the hexadecimal response to binary
            .joinToString("").apply { grid.add(this) } //make a single string, add to grid
            .sumOf { it.digitToIntOrNull()!! } //sum the binary 1s and 0s
    })
    var n = 0
    grid.forEachIndexed { y, line ->
        line.forEachIndexed loop@{ x, char ->
            if (Pair(x, y) in seen) return@loop
            if (char == '0') return@loop
            n += 1
            dfs(x, y)
        }
    }
    println("Answer part 2: $n")
}

fun dfs(x: Int, y: Int) {
    if (Pair(x, y) in seen) return
    if (grid[y][x] == '0') return
    seen.add(Pair(x, y))
    if (x > 0) dfs(x - 1, y)
    if (y > 0) dfs(x, y - 1)
    if (x < 127) dfs(x + 1, y)
    if (y < 127) dfs(x , y + 1)

}

private const val input = "ffayrhll"