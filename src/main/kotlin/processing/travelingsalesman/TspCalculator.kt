package com.gurrit.processing.travelingsalesman

class TspCalculator {

    fun findBestRoute(graph: List<TspVertice>): TspGraph {
        val lookup =  graph.groupBy { it.from }

        return with(lookup) {
            graph.map { findBestRoute(TspGraph(it)) }.maxBy { it.totalWeigh }
        }
    }

    private fun GraphLookup.findBestRoute(traversedRoute: TspGraph): TspGraph {
        val allSegments = this
        val traversedNodes = traversedRoute.asNodeList()
        val routes = if (traversedNodes.toSet() == allSegments.keys) {
            // If we've found a path that contains all nodes, we just have to add the return-to-start-path, we assume that only one such path exists
            val finalSegment = allSegments[traversedNodes.last()]!!
                .first { it.sameNodes(traversedNodes.first(), traversedNodes.last()) }
            listOf(traversedRoute + finalSegment)
        } else
            // Else we add another node that we don't already have in the traversed graph and is neighbor to the last node
            allSegments[traversedNodes.last()]!!
                .filterNot { availableRoute -> (traversedNodes).contains(availableRoute.to) }
                .map { possibleRoute -> findBestRoute(traversedRoute + possibleRoute) }

        return routes.maxBy { it.totalWeigh }
    }

}

private typealias GraphLookup = Map<String, List<TspVertice>>