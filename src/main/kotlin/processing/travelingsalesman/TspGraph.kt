package com.gurrit.processing.travelingsalesman

data class TspGraph(
    val traversedPath: List<TspVertice>,
    val totalWeigh: Int
) {

    constructor(vertice: TspVertice) : this(listOf(vertice), vertice.weight)

    infix operator fun plus(nextSegment: TspVertice): TspGraph {
        return TspGraph(traversedPath + nextSegment, totalWeigh + nextSegment.weight)
    }

    fun asNodeList(): List<String> = this.traversedPath
        .fold(emptyList<String>()) { acc, segment -> acc + segment.from + segment.to }
        .distinct()

}