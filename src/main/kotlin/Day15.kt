fun main() {
    var startA = 703L; var startB = 516L; var matches = 0
    repeat(40000000) {
        startA = generatorA(startA)
        startB = generatorB(startB)
        if(judge(startA, startB)) matches += 1
    }
    println(matches)
    startA = 703; startB = 516; matches = 0
    val a = mutableListOf<Long>(); val b = mutableListOf<Long>()
    while (a.size < 5000000 || b.size < 5000000) {
        startA = generatorA(startA)
        startB = generatorB(startB)
        if (startA%4 == 0L) a.add(startA)
        if (startB%8 == 0L) b.add(startB)
    }
    println((0..4999999).sumOf {
        if (judge(a[it], b[it])) 1L else 0L
    })
}

fun generatorA(n: Long): Long {
    return (n * 16807) % 2147483647
}

fun generatorB(n: Long): Long {
    return (n * 48271) % 2147483647
}

fun judge(a: Long, b: Long): Boolean {
    return a.toString(2).takeLast(16) == b.toString(2).takeLast(16)

}