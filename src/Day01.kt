import kotlin.math.abs

fun part1(input: List<String>): Int {
    infix fun Int.distanceTo(other: Int): Int = abs(this - other)
    fun distances(first: List<Int>, second: List<Int>): List<Int> =
        first.zip(second) { a, b -> a distanceTo b }

    val pairs = input.map { it.split("   ") }

    return distances(
        pairs.map { it.first().toInt() }.sorted(),
        pairs.map { it.last().toInt() }.sorted()
    ).sum()
}

fun part2(input: List<String>): Int {
    val pairs = input.map { it.split("   ") }

    val secondList = pairs.map { it.last().toInt() }
    val secondCounts = secondList.toSet()
        .map { number -> number to secondList.count { it == number } }
        .associate { it }

    return pairs
        .map { it.first().toInt() }
        .map { number -> number * (secondCounts[number] ?: 0) }
        .sum()
}

fun main() {
    val testInput = readInput("Day01_test")
    val input = readInput("Day01")

    check(part1(listOf("0   1")) == 1)

    // part1(testInput).println()
    check(part1(testInput) == 11)
    part1(input).println()

    // part2(testInput).println()
    check(part2(testInput) == 31)
    part2(input).println()
}
