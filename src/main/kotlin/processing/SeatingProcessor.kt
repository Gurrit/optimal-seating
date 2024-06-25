package com.gurrit.processing

import com.gurrit.model.SeatingNeighbors
import com.gurrit.model.CompleteSeating
import com.gurrit.model.SeatingPair
import com.gurrit.processing.travelingsalesman.TspCalculator
import com.gurrit.processing.travelingsalesman.TspGraph
import com.gurrit.processing.travelingsalesman.TspVertice

class SeatingProcessor(private val tspCalculator: TspCalculator) {

    /**
     * reduce the happiness unit of a seating arrangement by summing the happiness value that person1 gets from person2
     * And vice versa. In the case of correct seating
     */
    fun sumNeighborSeatingValues(seatings: List<SeatingNeighbors>): List<SeatingNeighbors> = seatings
        .groupBy { it.seatingPair }
        .flatMap { seatingPair ->
            seatingPair.value.map { it.copy(
                happinessValue = seatingPair.value.sumOf { it.happinessValue })
            }
    }

    fun findOptimalSeating(seatings: List<SeatingNeighbors>): CompleteSeating {
        val tspVertices = seatings.toTspVertices()
        val optimalNeighbors = tspCalculator.findBestRoute(tspVertices)
        return optimalNeighbors.toSeating()
    }

    private fun List<SeatingNeighbors>.toTspVertices(): List<TspVertice> = this.map { seating ->
        TspVertice(
            from = seating.seatingPair.person1,
            to = seating.seatingPair.person2,
            weight = seating.happinessValue
        )
    }

    private fun TspGraph.toSeating() = CompleteSeating(
        this.traversedPath.map { SeatingPair(it.from, it.to) },
        this.totalWeigh
    )

}