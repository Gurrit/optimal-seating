package processing

import com.gurrit.model.Seating
import com.gurrit.model.SeatingNeighbors
import com.gurrit.processing.SeatingProcessor
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SeatingProcessorTest {

    private val seatingProcessor = SeatingProcessor()

    @Test
    fun testReduceSeatingSameNeighbors() {
        val person1 = "Alice"
        val person2 = "Bob"
        val happiness1 = 30
        val happiness2 = 50

        val seating1 = Seating(
            seatingNeighbors = SeatingNeighbors(person1, person2),
            happinessUnit = happiness1
        )
        val seating2 = Seating(
            seatingNeighbors = SeatingNeighbors(person1, person2),
            happinessUnit = happiness2
        )
        val actual = seatingProcessor.sumNeighborValues(listOf(seating1, seating2))
        actual shouldHaveSize 1
        actual.first().happinessUnit shouldBe (happiness1 + happiness2)

    }

    @Test
    fun testReduceSeatingDifferentNeighbors() {
        val person1 = "Alice"
        val person2 = "Bob"
        val happiness1 = 30
        val happiness2 = 50

        val seating1 = Seating(
            seatingNeighbors = SeatingNeighbors(person1, person2),
            happinessUnit = happiness1
        )
        val seating2 = Seating(
            seatingNeighbors = SeatingNeighbors(person1, "Charles"),
            happinessUnit = happiness2
        )
        val actual = seatingProcessor.sumNeighborValues(listOf(seating1, seating2))
        actual shouldHaveSize 2
    }
}