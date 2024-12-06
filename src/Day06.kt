import Direction.DOWN
import Direction.LEFT
import Direction.RIGHT
import Direction.UP

enum class Direction {
    UP, RIGHT, DOWN, LEFT;
}

typealias Area = Array<CharArray>

data class Guard(
    val x: Int = -1,
    val y: Int = -1,
    val direction: Direction = UP,
)

fun Guard.isInArea(area: Area) = y in area.indices && x in area.first().indices

fun Guard.mark(area: Area) {
    if (area[y][x] != OBSTACLE) area[y][x] = MARKED
}

fun Guard.step() = when (direction) {
    UP -> copy(y = y - 1)
    RIGHT -> copy(x = x + 1)
    DOWN -> copy(y = y + 1)
    LEFT -> copy(x = x - 1)
}

fun Guard.isObstacle(area: Area): Boolean = area[y][x] == OBSTACLE

fun Guard.turn(): Guard = copy(
    direction = when (direction) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
    }
)

const val GUARD = '^'
const val MARKED = 'X'
const val OBSTACLE = '#'

private fun part1(input: List<String>): Int {
    fun findGuard(field: List<String>): Guard {
        field.forEachIndexed { y, row ->
            if (GUARD in row)
                row.forEachIndexed { x, pos ->
                    if (pos == GUARD) return Guard(x, y)
                }
        }
        return Guard() // invalid position, we did not find the guard
    }

    var guard = findGuard(input)
    val map = input.map { it.toCharArray() }.toTypedArray()
    while (guard.isInArea(map)) {
        guard.mark(map) // spot was stepped on

        val step = guard.step()
        guard = if (step.isInArea(map) && step.isObstacle(map))
            guard.turn()
        else
            step
    }

    return map.sumOf { row -> row.count { pos -> pos == MARKED } }
}

private fun part2(input: List<String>): Int {
    return 0
}

fun main() {
    val testInput = readInput("Day06_test")
    val input = readInput("Day06")


    part1(testInput).println()
    check(part1(testInput) == 41)
    part1(input).println()

    //check(part2(listOf("don't()mul(4,2)-do()mul(1,2)yhf")) == 2)

    //part2(testInput).println()
    //check(part2(testInput) == 48)
    //part2(input).println()
}
