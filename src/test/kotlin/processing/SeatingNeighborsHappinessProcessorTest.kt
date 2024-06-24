package processing

import com.gurrit.model.SeatingNeighborsHappiness
import com.gurrit.model.SeatingNeighbors
import com.gurrit.processing.SeatingProcessor
import com.gurrit.processing.travelingsalesman.TspCalculator
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SeatingNeighborsHappinessProcessorTest {

    private val seatingProcessor = SeatingProcessor(TspCalculator())

    @Test
    fun testReduceSeatingSameNeighbors() {
        val person1 = "Alice"
        val person2 = "Bob"
        val happiness1 = 30
        val happiness2 = 50

        val seating1 = SeatingNeighborsHappiness(
            seatingNeighbors = SeatingNeighbors(person1, person2),
            happinessValue = happiness1
        )
        val seating2 = SeatingNeighborsHappiness(
            seatingNeighbors = SeatingNeighbors(person1, person2),
            happinessValue = happiness2
        )
        val actual = seatingProcessor.sumNeighborSeatingValues(listOf(seating1, seating2))
        actual shouldHaveSize 1
        actual.first().happinessValue shouldBe (happiness1 + happiness2)

    }

    @Test
    fun testReduceSeatingDifferentNeighbors() {
        val person1 = "Alice"
        val person2 = "Bob"
        val happiness1 = 30
        val happiness2 = 50

        val seating1 = SeatingNeighborsHappiness(
            seatingNeighbors = SeatingNeighbors(person1, person2),
            happinessValue = happiness1
        )
        val seating2 = SeatingNeighborsHappiness(
            seatingNeighbors = SeatingNeighbors(person1, "Charles"),
            happinessValue = happiness2
        )
        val actual = seatingProcessor.sumNeighborSeatingValues(listOf(seating1, seating2))
        actual shouldHaveSize 2
    }
}