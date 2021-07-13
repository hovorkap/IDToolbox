package hovorkap.idtoolbox

import hovorkap.idtoolbox.transliteration.TransliteratingConverter
import org.apache.commons.lang3.math.NumberUtils.toInt
import org.springframework.stereotype.Service

@Service
class MrzChecksumGenerator(private val converters:  List<TransliteratingConverter>) {

    fun calculateChecksum(input: String): Int {
        var sum = 0
        input.toCharArray().forEachIndexed { index, char -> sum += (listOf(7, 3, 1).get(index % 3) * characterToValue(char)) }
        return sum % 10
    }

    fun calculateChecksum(line1: String, line2: String): Int {
        return calculateChecksum(
                line1.substring(5, 30) +
                        line2.substring(0, 7) +
                        line2.substring(8, 15) +
                        line2.substring(18, 29))
    }

    fun normalize(input: String, length: Int): String = if (input.length > length) {
        throw Exception("Input too long")
    } else  {
        var output = input
        converters.forEach{ output = it.convert(output)}
        output.toUpperCase().replace(" ", "<") + "<".repeat(length - input.length)
    }

    fun characterToValue(char: Char): Int = if (char.isDigit()) {
        toInt(char.toString())
    } else if (char.isLetter()) {
        char.toUpperCase().toInt() - 'A'.toInt() + 10
    } else if (char == '<') {
        0
    } else if (char == '-') {
        0
    } else throw Exception("Unsupported character $char")
}
