package processing.travelingsalesman

import com.gurrit.processing.travelingsalesman.TspCalculator
import com.gurrit.processing.travelingsalesman.TspVertice
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class TspCalculatorTest {

    private val sut = TspCalculator()

    @Test
    fun findsBestRoute() {
        val res = sut.findBestRoute(input)

        res.totalWeigh shouldBe 201
        res.asNodeList() shouldBe listOf("A", "B", "C", "D")

    }

    private val input = listOf(
        TspVertice("A", "B", 1),
        TspVertice("A", "C", -1),
        TspVertice("A", "D", 100),
        TspVertice("B", "A", 1),
        TspVertice("B", "C", 50),
        TspVertice("B", "D", -20),
        TspVertice("C", "A", -1),
        TspVertice("C", "B", 50),
        TspVertice("C", "D", 50),
        TspVertice("D", "A", 100),
        TspVertice("D", "B", -20),
        TspVertice("D", "C", 50),
    )
}