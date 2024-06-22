package com.gurrit

import com.gurrit.parser.SeatingParser
import com.gurrit.processing.SeatingProcessor
import com.gurrit.processing.traveling_salesman.TspCalculator

fun main() {

    val seatingParser = SeatingParser()
    val tspCalculator = TspCalculator()
    val seatingProcessor = SeatingProcessor(tspCalculator)

    val optimalSeating = OptimalSeating(seatingParser, seatingProcessor)
    optimalSeating.calculateOptimalSeating("optimal-seating.txt")

}