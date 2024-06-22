package com.gurrit.processing

import com.gurrit.model.Seating
import com.gurrit.model.SeatingNeighbors
import com.gurrit.model.SeatingTraversedGraph

class SeatingProcessor {

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
        val optimalSeating = seatings.findBestRoute()
        return optimalSeating
    }

    private fun List<Seating>.findBestRoute(): SeatingTraversedGraph {
        val seatingLookup = this.groupBy { it.seatingNeighbors.person1 }
        return this.map { seatingLookup.findBestRoute(SeatingTraversedGraph(it)) }
            .maxBy { it.totalHappiness }
    }

    private fun Map<String, List<Seating>>.findBestRoute(traversedRoute: SeatingTraversedGraph): SeatingTraversedGraph {
        val allSegments = this
        val routes = if (traversedRoute.allNodes().size == this.size) {
            // If we've found a path that contains all nodes, we just have to add the return-to-start-path, we assume that only one path exists
            val finalSegment = this[traversedRoute.lastNode()]!!
                .first { it.seatingNeighbors == SeatingNeighbors(traversedRoute.firstNode(), traversedRoute.lastNode()) }
            listOf(traversedRoute.addSegment(finalSegment))
        }
        else {
            // Else we add another node that we don't already have in the traversed graph
            allSegments[traversedRoute.lastNode()]!!
                .filterNot { availableRoute -> (traversedRoute.allNodes()).contains(availableRoute.seatingNeighbors.person2) }
                .map { possibleRoute -> findBestRoute(traversedRoute.addSegment(possibleRoute)) }
        }

        return routes.maxBy { it.totalHappiness }
    }





}