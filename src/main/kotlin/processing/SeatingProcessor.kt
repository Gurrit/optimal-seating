package com.gurrit.processing

import com.gurrit.model.SeatingNeighborsHappiness
import com.gurrit.model.Seating
import com.gurrit.model.SeatingNeighbors
import com.gurrit.processing.travelingsalesman.TspCalculator
import com.gurrit.processing.travelingsalesman.TspGraph
import com.gurrit.processing.travelingsalesman.TspVertice

class SeatingProcessor(private val tspCalculator: TspCalculator) {

    /**
     * reduce the happiness unit of a seating arrangement by summing the happiness value that person1 gets from person2
     * And vice versa. In the case of correct seating
     */
    fun sumNeighborSeatingValues(seatings: List<SeatingNeighborsHappiness>): List<SeatingNeighborsHappiness> = seatings
        .groupBy { it.seatingNeighbors }
        .flatMap { seatingPair ->
            seatingPair.value.map { it.copy(
                happinessValue = seatingPair.value.sumOf { it.happinessValue })
            }
    }

    fun findOptimalSeating(seatings: List<SeatingNeighborsHappiness>): Seating {
        val tspVertices = seatings.toTspVertices()
        val optimalNeighbors = tspCalculator.findBestRoute(tspVertices)
        return optimalNeighbors.toSeating()
    }

    private fun List<SeatingNeighborsHappiness>.toTspVertices(): List<TspVertice> = this.map { seating ->
        TspVertice(
            from = seating.seatingNeighbors.person1,
            to = seating.seatingNeighbors.person2,
            weight = seating.happinessValue
        )
    }

    private fun TspGraph.toSeating() = Seating(
        this.traversedPath.map { SeatingNeighbors(it.from, it.to) },
        this.totalWeigh
    )

}