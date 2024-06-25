package com.gurrit.parser

import com.gurrit.model.SeatingNeighbors
import com.gurrit.model.SeatingPair

private const val LINE_TEMPLATE = "^(\\w+) would (\\w+) (\\d+) happiness units by sitting next to (\\w+)."

class SeatingParser {

    private val pattern = LINE_TEMPLATE.toRegex()

    fun parseSeatingLine(line: String): SeatingNeighbors? {
        if (line.isBlank()) return null
        if (line.lines().size != 1) throw IllegalArgumentException("Only a single line should be parsed at a time")

        val match = pattern.findAll(line).firstOrNull() ?: throw IllegalArgumentException("Could not parse input line $line")

        // First in regex match is always full line
        val (person1, unitModifier, units, person2) = match.groupValues.drop(1)

        return SeatingNeighbors(
            seatingPair = SeatingPair(person1, person2),
            happinessValue =  unitModifier.asSign() * units.toInt()
        )
    }

    private fun String.asSign(): Int = when (this) {
        "gain" -> 1
        "lose" -> -1
        else ->throw IllegalArgumentException("$this is not a valid operation, should be \"gain\" or \"lose\"")
    }
}