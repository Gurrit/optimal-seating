package com.gurrit

import com.gurrit.model.Seating
import com.gurrit.parser.SeatingParser
import com.gurrit.processing.SeatingProcessor
import kotlin.io.path.Path
import kotlin.io.path.readLines

class OptimalSeatingService(
    private val seatingParser: SeatingParser,
    private val seatingProcessor: SeatingProcessor
) {

    fun calculateOptimalSeating(filePath: String): Seating {
        val seatings = Path(filePath).readLines().mapNotNull { seatingParser.parseSeatingLine(it) }
        val reducedSeatings = seatingProcessor.sumNeighborSeatingValues(seatings)

        return seatingProcessor.findOptimalSeating(reducedSeatings)

    }
}