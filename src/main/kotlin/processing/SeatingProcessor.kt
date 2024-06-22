package com.gurrit.processing

import com.gurrit.model.Seating
import com.gurrit.model.SeatingNeighbors
import com.gurrit.model.SeatingTraversedGraph
import com.gurrit.processing.traveling_salesman.TspCalculator
import com.gurrit.processing.traveling_salesman.TspGraph
import com.gurrit.processing.traveling_salesman.TspVertice

class SeatingProcessor(private val tspCalculator: TspCalculator) {

    /**
     * reduce the happiness unit of a seating arrangement by summing the happiness value that person1 gets from person2
     * And vice versa. In the case of correct seating
     */
    fun sumNeighborValues(seatings: List<Seating>): List<Seating> = seatings
        .groupBy { it.seatingNeighbors }
        .flatMap { seatingPair ->
            seatingPair.value.map { it.copy(
                happinessUnit = seatingPair.value.sumOf { it.happinessUnit })
            }
    }

    fun calculateTsp(seatings: List<Seating>): SeatingTraversedGraph {
        val optimalSeating = findBestRoute(seatings)
        return optimalSeating
    }

    private fun findBestRoute(seatings: List<Seating>): SeatingTraversedGraph {
        val tspVertices = seatings.map { TspVertice(
            from = it.seatingNeighbors.person1,
            to = it.seatingNeighbors.person2,
            weight = it.happinessUnit)
        }
        seatings
            .map { tspCalculator.findBestRoute(tspVertices) }
            .maxBy { it.totalWeigh }
        TODO()
    }

}