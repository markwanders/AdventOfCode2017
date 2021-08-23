import java.io.File

fun main() {
    val passphrases = input.map { passphrase -> passphrase.split(" ") }
    println(passphrases.filter { passphrase -> passphrase.distinct() == passphrase }.size)
    println(passphrases.filter { passphrase ->
        passphrase.distinctBy {
            it.toCharArray().sorted().joinToString("") // words are anagrams if their content, sorted alphabetically, is identical
        } == passphrase
    }.size)
}

private val input = File("src/main/resources/day04.txt").readLines()