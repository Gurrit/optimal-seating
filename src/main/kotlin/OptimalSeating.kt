package com.gurrit

import com.gurrit.parser.SeatingParser
import com.gurrit.processing.SeatingProcessor
import kotlin.io.path.Path
import kotlin.io.path.readLines

class OptimalSeating(
    private val seatingParser: SeatingParser,
    private val seatingProcessor: SeatingProcessor
) {

    fun calculateOptimalSeating(filePath: String) {
        val seatings = Path(filePath).readLines().mapNotNull { seatingParser.parseSeatingLine(it) }
        val reducedSeatings = seatingProcessor.sumNeighborValues(seatings)

        println(seatingProcessor.calculateTsp(reducedSeatings))

    }
}