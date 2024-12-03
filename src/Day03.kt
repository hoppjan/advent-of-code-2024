private fun String.multiply() =
    drop(4).dropLast(1).split(",").map { it.toInt() }.let { (a, b) -> a * b }

private fun part1(input: List<String>): Int {
    return "(mul[(][0-9]+,[0-9]+[)])".toRegex()
        .findAll(input.joinToString(separator = ""))
        .map {
            it.value.multiply()
        }
        .sum()
}

private fun part2(input: List<String>): Int {
    var enabled = true
    return "(mul[(][0-9]+,[0-9]+[)]|don't[(][)]|do[(][)])".toRegex()
        .findAll(input.joinToString(separator = ""))
        .map { it.value }
        .sumOf { instruction ->
            if (instruction.startsWith("mul")) {
                if (enabled) instruction.multiply() else 0
            } else {
                enabled = !instruction.startsWith("don")
                0
            }
        }
}

fun main() {
    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    check(part1(listOf("mul(1,2)")) == 2)

    part1(testInput).println()
    check(part1(testInput) == 161)
    part1(input).println()

    check(part2(listOf("don't()mul(4,2)-do()mul(1,2)yhf")) == 2)

    part2(testInput).println()
    check(part2(testInput) == 48)
    part2(input).println()
}
