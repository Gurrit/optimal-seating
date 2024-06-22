package com.gurrit.model

data class SeatingTraversedGraph(
    val traversedPath: List<Seating>,
    val totalHappiness: Int
){

    constructor(seating: Seating) : this(listOf(seating), seating.happinessUnit)

    fun addSegment(nextSegment: Seating): SeatingTraversedGraph {
        return SeatingTraversedGraph(traversedPath + nextSegment, totalHappiness + nextSegment.happinessUnit)
    }

    fun lastNode(): String = this.traversedPath.last().seatingNeighbors.person2

    fun allNodes(): List<String> = this.traversedPath.flatMap {
        listOf(it.seatingNeighbors.person1, it.seatingNeighbors.person2)
    }.distinct()

    fun firstNode(): String = this.traversedPath.first().seatingNeighbors.person1

}