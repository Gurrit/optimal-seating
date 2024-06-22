package com.gurrit.processing.traveling_salesman

class TspCalculator {
    // TODO Naming

    fun findBestRoute(graph: List<TspVertice>): TspGraph {
       val lookup =  graph.groupBy { it.from }

        return graph.map { lookup.findBestRoute(TspGraph(it)) }.maxBy { it.totalWeigh }

    }

    // TODO naming
    private fun Map<String, List<TspVertice>>.findBestRoute(traversedRoute: TspGraph): TspGraph {
        val allSegments = this
        val routes = if (traversedRoute.allNodes().size == this.size) {
            // If we've found a path that contains all nodes, we just have to add the return-to-start-path, we assume that only one path exists
            val finalSegment = allSegments[traversedRoute.lastNode()]!!
                .first { it == TspVertice(traversedRoute.firstNode(), traversedRoute.lastNode(), 0) }
            listOf(traversedRoute.addSegment(finalSegment))
        } else {
            // Else we add another node that we don't already have in the traversed graph
            allSegments[traversedRoute.lastNode()]!!
                .filterNot { availableRoute -> (traversedRoute.allNodes()).contains(availableRoute.to) }
                .map { possibleRoute -> findBestRoute(traversedRoute.addSegment(possibleRoute)) }
        }

        return routes.maxBy { it.totalWeigh }
    }
}