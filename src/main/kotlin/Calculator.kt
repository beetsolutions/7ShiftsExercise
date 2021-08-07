
class Calculator {

    fun add(numbers: String): Int {
        return when (numbers) {
            "" -> 0
            else -> {
                if (numbers.startsWith("//")) {
                    val endInclusive = numbers.indexOf("\\n") - 1
                    val delimiter = numbers.substring(IntRange(2, endInclusive))
                    sumNumbers(numbers, delimiter)
                } else {
                    sumNumbers(numbers)
                }
            }
        }
    }

    private fun sumNumbers(numbers: String, extraDelimiters: String = ","): Int {

        val delimiters = extraDelimiters
            .split("")
            .filter {
                it.isNotEmpty()
            }
            .toTypedArray()

        val nonDelimiterNumbers = numbers
            .split("\\n", *delimiters)
            .map {
                it.toIntOrNull() ?: 0
            }

        val illegalNumbers = nonDelimiterNumbers.filter {
            it < 0
        }

        if (illegalNumbers.isNotEmpty()) {
            throw IllegalArgumentException("Negative numbers not allowed, you entered the following negative numbers: $illegalNumbers")
        }

        val listOfIntegers = nonDelimiterNumbers.map {
            if (it > 1000) {
                0
            } else {
                it
            }
        }
        return listOfIntegers.sum()
    }
}