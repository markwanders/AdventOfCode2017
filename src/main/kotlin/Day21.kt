import java.io.File

private val input = File("src/main/resources/day21.txt").readLines()
val rules = input.associateBy({ it.split(" => ").first() }, { it.split(" => ").last() }).toMap()

fun main() {
    var grid: List<List<Char>> = ruleToGrid(".#./..#/###")
    printGrid(grid)
    repeat(2) {

        if (grid.size % 2 == 0) {
            val times = grid.size / 2
            //todo: split into subgrids, iterate over those to find the replacements, construct resulting grid
            val subGrid = grid.map { it.take(2) }.take(2)
            val matchingRule = matchGridToRule(subGrid)
            println(matchingRule)
            val replacement = rules[matchingRule]!!
            val newGrid = ruleToGrid(replacement).toMutableList()
            repeat(times - 1) {
                newGrid.addAll(newGrid)
                newGrid.map { it + it }
            }
            grid = newGrid
        } else if (grid.size % 3 == 0) {
            val subGrid = grid.map { it.take(3) }.take(3)
            val matchingRule = matchGridToRule(subGrid)
            val replacement = rules[matchingRule]!!
            val times = grid.size / 3
            val newGrid = ruleToGrid(replacement).toMutableList()
            repeat(times - 1) {
                newGrid.addAll(newGrid)
                newGrid.map { it + it }
            }
            grid = newGrid
        }
        printGrid(grid)

    }
}


fun matchGridToRule(grid: List<List<Char>>) = rules.keys.first { functions.any { function -> gridToRule(function.call(grid)) == it} }

fun flipHorizontal(grid: List<List<Char>>): List<List<Char>> = grid.map { it.reversed() }

fun flipVertical(grid: List<List<Char>>): List<List<Char>> = grid.reversed()

fun gridToRule(grid: List<List<Char>>) = grid.joinToString("/") { it.joinToString("") }

fun ruleToGrid(rule: String) = rule.split("/").map { it.toCharArray().toList() }

fun transpose(grid: List<List<Char>>) : List<List<Char>> {
    val transpose = MutableList(grid.size) { MutableList(grid.size) {'.'} }
    for(i in transpose.indices){
        for(j in transpose[i].indices){
            transpose[i][j] = grid[j][i]
        }
    }
    return transpose
}

fun rotate90(grid: List<List<Char>>) : List<List<Char>> = flipHorizontal(transpose(grid))

fun rotateMinus90(grid: List<List<Char>>) : List<List<Char>> = flipVertical(transpose(grid))

fun rotate180(grid: List<List<Char>>) : List<List<Char>> = flipHorizontal(flipVertical(grid))

fun rotate90AndHorizontalFlip(grid: List<List<Char>>) = transpose(grid)

fun rotate90AndVerticalFlip(grid: List<List<Char>>) = flipVertical(rotate90(grid))

fun rotateMinus90AndHorizontalFlip(grid: List<List<Char>>) = flipHorizontal(rotate90(grid))

fun printGrid(grid: List<List<Char>>) {
    grid.forEach { println(it.joinToString(""))}
    println()
}
val functions = listOf(::flipHorizontal, ::flipVertical, ::rotate90, ::rotate180, ::rotateMinus90, ::rotate90AndHorizontalFlip, ::rotate90AndVerticalFlip, ::rotateMinus90AndHorizontalFlip)