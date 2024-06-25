package com.gurrit.model

data class CompleteSeating(
    val seatingNeighbors: List<SeatingPair>,
    val totalHappiness: Int
){

    override fun toString(): String {
        return """
           
            TotalHappiness: $totalHappiness
            SeatingPositions: ${ formatSeatingPositions() }
        """
    }

    private fun formatSeatingPositions(): String {
        return seatingNeighbors.joinToString(separator = " - ") {
            it.person1
        }
    }
}