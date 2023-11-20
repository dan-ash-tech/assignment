fun numberToWords(number: Int): String {
    if (number == 0) {
        return "zero"
    }

    val units = arrayOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val teens = arrayOf("eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    val tens = arrayOf("", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    val million = number / 1000000
    val thousand = (number % 1000000) / 1000
    val remainder = number % 1000

    val result = StringBuilder()

    fun convertThreeDigits(num: Int, suffix: String): String {
        val hundreds = num / 100
        val tensAndUnits = num % 100

        val words = StringBuilder()

        if (hundreds > 0) {
            words.append("${units[hundreds]} hundred ")
        }

        if (tensAndUnits > 0) {
            if (tensAndUnits in 11..19) {
                words.append(teens[tensAndUnits - 11])
            } else {
                val tensDigit = tensAndUnits / 10
                val unitsDigit = tensAndUnits % 10

                if (tensDigit > 0) {
                    words.append(tens[tensDigit])
                }

                if (unitsDigit > 0) {
                    words.append(units[unitsDigit])
                }
            }
        }

        return words.toString().trim() + " $suffix "
    }

    if (million > 0) {
        result.append(convertThreeDigits(million, "million"))
    }

    if (thousand > 0) {
        result.append(convertThreeDigits(thousand, "thousand"))
    }

    if (remainder > 0) {
        result.append(convertThreeDigits(remainder, ""))
    }

    return result.toString().trim()
}

fun main() {
    print("Enter an integer (up to six digits): ")
    val input = readlnOrNull()

    try {
        val number = input?.toInt() ?: throw NumberFormatException("Invalid input")

        if (number < 0 || number > 999999) {
            throw IllegalArgumentException("Number should be between 0 and 999999")
        }

        val result = numberToWords(number)
        println("In words: $result")
    } catch (e: NumberFormatException) {
        println("Invalid input. Please enter a valid integer.")
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}
