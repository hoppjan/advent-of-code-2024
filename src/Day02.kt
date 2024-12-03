import kotlin.math.abs

private fun List<Int>.classify(): Boolean {
    val rollingChanges = windowed(size = 2) { (a, b) -> a - b }
    return (rollingChanges.all { it < 0 } || rollingChanges.all { it > 0 }) &&
            rollingChanges.all { abs(it) in listOf(1, 2, 3) }
}

private fun part1(input: List<String>): Int {
    val reports = input.map { it.split(" ").map { it.toInt() } }
    return reports.map { it.classify() }.count { it }
}

private fun part2(input: List<String>): Int {
    fun List<Int>.createWithOneLevelRemoved(): List<List<Int>> {
        fun List<Int>.removedAt(index: Int): List<Int> =
            this@removedAt.toMutableList().apply { removeAt(index) }.toList()

        return buildList {
            for (i in this@createWithOneLevelRemoved.indices)
                add(this@createWithOneLevelRemoved.removedAt(i))
        }
    }

    val reports = input.map { it.split(" ").map { it.toInt() } }
    return reports
        .map {
            it.createWithOneLevelRemoved()
                .any { it.classify() }
        }
        .count { it }
}

fun main() {
    val testInput = readInput("Day02_test")
    val input = readInput("Day02")

    check(part1(listOf("0 1 3")) == 1)

    // part1(testInput).println()
    check(part1(testInput) == 2)
    part1(input).println()

    // part2(testInput).println()
    check(part2(testInput) == 4)
    part2(input).println()
}
