package parser

import com.gurrit.model.SeatingNeighbors
import com.gurrit.model.SeatingPair
import com.gurrit.parser.SeatingParser
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.jupiter.api.Test

class CompleteSeatingPairHappinessParserTest {

    private val sut: SeatingParser = SeatingParser()

    @Test
    fun testParseGainLine() {
        val testData = "Alice would gain 54 happiness units by sitting next to Bob."
        val seatingNeighbors = SeatingPair("Alice", "Bob")
        val expected = SeatingNeighbors(seatingNeighbors, 54)

        val actual = sut.parseSeatingLine(testData)

        actual.shouldNotBeNull()
        actual shouldBeEqual actual

    }
    @Test
    fun testParseLoseLine() {
        val testData = "Alice would lose 54 happiness units by sitting next to Bob."
        val seatingNeighbors = SeatingPair("Alice", "Bob")
        val expected = SeatingNeighbors(seatingNeighbors, -54)

        val actual = sut.parseSeatingLine(testData)

        actual.shouldNotBeNull()
        actual shouldBeEqual expected
    }

    @Test
    fun testBadInput() {
        val testData = "Alice is bad at determining how she feels, 0 happiness units by sitting next to Bob."
        val seatingNeighbors = SeatingPair("Alice", "Bob")

        shouldThrow<IllegalArgumentException> { sut.parseSeatingLine(testData) }

    }

}

