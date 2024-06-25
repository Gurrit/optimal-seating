package parser

import com.gurrit.model.SeatingNeighborsHappiness
import com.gurrit.model.SeatingNeighbors
import com.gurrit.parser.SeatingParser
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.jupiter.api.Test

class SeatingNeighborsHappinessParserTest {

    private val sut: SeatingParser = SeatingParser()

    @Test
    fun testParseGainLine() {
        val testData = "Alice would gain 54 happiness units by sitting next to Bob."
        val seatingNeighbors = SeatingNeighbors("Alice", "Bob")
        val expected = SeatingNeighborsHappiness(seatingNeighbors, 54)

        val actual = sut.parseSeatingLine(testData)

        actual.shouldNotBeNull()
        actual shouldBeEqual actual

    }
    @Test
    fun testParseLoseLine() {
        val testData = "Alice would lose 54 happiness units by sitting next to Bob."
        val seatingNeighbors = SeatingNeighbors("Alice", "Bob")
        val expected = SeatingNeighborsHappiness(seatingNeighbors, -54)

        val actual = sut.parseSeatingLine(testData)

        actual.shouldNotBeNull()
        actual shouldBeEqual expected
    }

    @Test
    fun testBadInput() {
        TODO()
    }

}

