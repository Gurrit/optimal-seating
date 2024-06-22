package com.gurrit

import com.gurrit.parser.SeatingParser
import com.gurrit.processing.SeatingProcessor

fun main() {

    val seatingParser = SeatingParser()
    val seatingProcessor = SeatingProcessor()

    val optimalSeating = OptimalSeating(seatingParser, seatingProcessor)
    optimalSeating.calculateOptimalSeating("optimal-seating.txt")

}