import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class CalculatorTest {

    private val sut = Calculator()

    @ParameterizedTest
    @MethodSource("additionTestPredictions")
    fun testAdd(numbers: String, expected: Int) {
        val add = sut.add(numbers)
        assertEquals(add, expected)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "1,2,-5",
            "//;\\n1;-3;4"
        ]
    )
    fun testAddWithNegativeNumbersShouldThrowIllegalArgumentException(numbers: String) {
        assertThrows(IllegalArgumentException::class.java) {
            sut.add(numbers)
        }
    }

    companion object {
        @JvmStatic
        fun additionTestPredictions() = listOf(
            Arguments.of("1,2,5", 8),
            Arguments.of("//;\\n1;3;4", 8),
            Arguments.of("//$,@\\n1$2@3", 6),
            Arguments.of("//***\\n1***2***3", 6),
            Arguments.of("//\$\\n1\$2\$3", 6),
            Arguments.of("1\\n,2,3", 6),
            Arguments.of("", 0),
            Arguments.of("1,\\n2,4", 7),
            Arguments.of("//@\\n2@3@8", 13),
        )
    }
}