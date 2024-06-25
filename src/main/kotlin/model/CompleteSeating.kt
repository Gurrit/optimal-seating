package com.gurrit.model

data class Seating(
    val seatingNeighbors: List<SeatingNeighbors>,
    val totalHappiness: Int
){

    override fun toString(): String {
        return """
           
            TotalHappiness: $totalHappiness
            SeatingPositions: ${ formatSeatingPositions() }
        """
    }

    private fun formatSeatingPositions(): String {
        return seatingNeighbors.joinToString(separator = " - ", postfix = " - ${ seatingNeighbors.last().person2 }") {
            it.person1
        }
    }
}