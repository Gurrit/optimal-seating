package com.gurrit

import com.gurrit.parser.SeatingParser
import com.gurrit.processing.SeatingProcessor
import com.gurrit.processing.travelingsalesman.TspCalculator


private const val FILE_PATH = "optimal-seating.txt"

fun main() {

    val seatingParser = SeatingParser()
    val tspCalculator = TspCalculator()
    val seatingProcessor = SeatingProcessor(tspCalculator)

    val optimalSeatingService = OptimalSeatingService(seatingParser, seatingProcessor)
    val optimalSeating = optimalSeatingService.calculateOptimalSeating(FILE_PATH)

    println("The optimal seating found was the following: $optimalSeating")


}