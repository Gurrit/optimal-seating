package com.gurrit.processing.traveling_salesman

import com.gurrit.model.Seating
import com.gurrit.model.SeatingTraversedGraph

data class TspGraph(
    val traversedPath: List<TspVertice>,
    val totalWeigh: Int
) {

    constructor(vertice: TspVertice) : this(listOf(vertice), vertice.weight)

    fun addSegment(nextSegment: TspVertice): TspGraph {
        return TspGraph(traversedPath + nextSegment, totalWeigh + nextSegment.weight)
    }

    fun lastNode(): String = this.traversedPath.last().to

    fun allNodes(): List<String> = this.traversedPath.flatMap {
        listOf(it.from, it.to)
    }.distinct()

    fun firstNode(): String = this.traversedPath.first().from


}