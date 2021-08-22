fun main() {
    println((0..127).sumOf { rowNumber ->
        hex("$input-$rowNumber".map { it.code }, 64) //knot hash the int value of the string
            .map { Integer.toBinaryString(it.toString().toInt(radix = 16)).padStart(4, '0') } //convert the hexadecimal response to binary
            .joinToString("").sumOf { it.digitToIntOrNull()!! } //sum the binary 1s and 0s
    })
}

private const val input = "ffayrhll"