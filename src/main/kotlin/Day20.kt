
import java.io.File
import kotlin.math.abs

fun main() {
    var particles = input.map {
        Regex("[-?\\d]+").findAll(it).map { matchResult -> matchResult.value.toLong() }.toList()
    }.toMutableList()
    repeat(700) { _ ->
        particles.forEachIndexed { index, particle ->
            run {
                val vX = particle[3] + particle[6]
                val vY = particle[4] + particle[7]
                val vZ = particle[5] + particle[8]
                val pX = particle[0] + vX
                val pY = particle[1] + vY
                val pZ = particle[2] + vZ
                particles[index] = listOf(pX, pY, pZ, vX, vY, vZ, particle[6], particle[7], particle[8])
            }
        }
        val occurrences = particles.groupingBy { it.take(3) }.eachCount().toMap() // comment out these two lines
        particles = particles.filter { occurrences[it.take(3)] == 1 }.toMutableList() // for part 1
    }
    println(particles.indexOf(particles.minByOrNull { manhattan(it) }))
    println(particles.size)
}

fun manhattan(particle: List<Long>): Long {
    return abs(particle[0]) + abs(particle[1]) + abs(particle[2])
}

private val input = File("src/main/resources/day20.txt").readLines()