fun main() {
    var a = 703L; var b = 516L; var matches = 0
    repeat(40000000) {
        a = generatorA(a)
        b = generatorB(b)
        matches += judge(a, b)
    }
    println(matches)
    a = 703; b = 516
    val aIterator = sequence {
        while(true) {
            a = generatorA(a)
            if (a % 4 == 0L) yield(a)
        }
    }.iterator()
    val bIterator = sequence {
        while(true) {
            b = generatorB(b)
            if (b % 8 == 0L) yield(b)
        }
    }.iterator()
    println((0..4999999).sumOf {
        judge(aIterator.next(), bIterator.next())
    })
}

fun generatorA(n: Long): Long {
    return (n * 16807) % 2147483647
}

fun generatorB(n: Long): Long {
    return (n * 48271) % 2147483647
}

fun judge(a: Long, b: Long): Int {
    return if(a.toString(2).takeLast(16) == b.toString(2).takeLast(16))  1 else  0

}