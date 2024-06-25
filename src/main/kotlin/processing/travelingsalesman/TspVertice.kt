package com.gurrit.processing.travelingsalesman

data class TspVertice(
    val from: String,
    val to: String,
    val weight: Int
) {

    fun sameNodes(otherFrom: String, otherTo: String): Boolean {
        return setOf(otherTo, otherFrom) == setOf(to, from)
    }
}