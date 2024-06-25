package processing

import com.gurrit.model.SeatingNeighbors
import com.gurrit.model.SeatingPair
import com.gurrit.processing.SeatingProcessor
import com.gurrit.processing.travelingsalesman.TspCalculator
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CompleteSeatingPairHappinessProcessorTest {

    private val seatingProcessor = SeatingProcessor(TspCalculator())

    @Test
    fun testReduceSeatingSameNeighbors() {
        val person1 = "Alice"
        val person2 = "Bob"
        val happiness1 = 30
        val happiness2 = 50

        val seating1 = SeatingNeighbors(
            seatingPair = SeatingPair(person1, person2),
            happinessValue = happiness1
        )
        val seating2 = SeatingNeighbors(
            seatingPair = SeatingPair(person1, person2),
            happinessValue = happiness2
        )
        val actual = seatingProcessor.sumNeighborSeatingValues(listOf(seating1, seating2))
        actual shouldHaveSize 2
        actual.first().happinessValue shouldBe (happiness1 + happiness2)
        actual.last().happinessValue shouldBe (happiness1 + happiness2)

    }

    @Test
    fun testReduceSeatingDifferentNeighbors() {
        val person1 = "Alice"
        val person2 = "Bob"
        val happiness1 = 30
        val happiness2 = 50

        val seating1 = SeatingNeighbors(
            seatingPair = SeatingPair(person1, person2),
            happinessValue = happiness1
        )
        val seating2 = SeatingNeighbors(
            seatingPair = SeatingPair(person1, "Charles"),
            happinessValue = happiness2
        )
        val actual = seatingProcessor.sumNeighborSeatingValues(listOf(seating1, seating2))
        actual shouldHaveSize 2
        actual.first().happinessValue shouldBe happiness1
        actual.last().happinessValue shouldBe happiness2
    }
}