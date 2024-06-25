package com.gurrit

import com.gurrit.model.CompleteSeating
import com.gurrit.parser.SeatingParser
import com.gurrit.processing.SeatingProcessor

class OptimalSeatingService(
    private val seatingParser: SeatingParser,
    private val seatingProcessor: SeatingProcessor
) {

    fun calculateOptimalSeating(input: String): CompleteSeating {
        val seatings = input.lines().mapNotNull { seatingParser.parseSeatingLine(it) }
        val reducedSeatings = seatingProcessor.sumNeighborSeatingValues(seatings)

        return seatingProcessor.findOptimalSeating(reducedSeatings)

    }
}