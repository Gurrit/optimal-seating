package com.gurrit.processing.travelingsalesman

data class TspVertice(
    val from: String,
    val to: String,
    val weight: Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TspVertice

        return this.hashCode() == other.hashCode()
                && this.weight == other.weight
    }

    override fun hashCode(): Int {
        val hash1 = from.hashCode() * to.hashCode()
        val hash2 = from.hashCode() + to.hashCode()
        return hash2 * hash1
    }

    fun sameNodes(otherFrom: String, otherTo: String): Boolean {
        return setOf(otherTo, otherFrom) == setOf(to, from)
    }
}