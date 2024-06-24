package com.gurrit.parser

import com.gurrit.model.SeatingNeighborsHappiness
import com.gurrit.model.SeatingNeighbors

private const val LINE_TEMPLATE = "^(\\w+) would (\\w+) (\\d+) happiness units by sitting next to (\\w+)."

class SeatingParser {

    private val pattern = LINE_TEMPLATE.toRegex()

    // TODO Naming
    fun parseSeatingLine(line: String): SeatingNeighborsHappiness? {
        if (line.isBlank()) return null
        if (line.lines().size != 1) throw IllegalArgumentException("Only a single line must be parsed at a time")

        val match = pattern.findAll(line).first()
        // First is always full line
        val (person1, unitModifier, units, person2) = match.groupValues.drop(1)

        return SeatingNeighborsHappiness(
            seatingNeighbors = SeatingNeighbors(person1, person2),
            happinessValue =  unitModifier.asSign() * units.toInt()
        )
    }

    private fun String.asSign(): Int = when (this) {
        "gain" -> 1
        "lose" -> -1
        // TODO Error message
        else -> println(this).let { throw IllegalArgumentException("$this is not a valid operation") }
    }
}