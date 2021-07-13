package hovorkap.idtoolbox

import hovorkap.idtoolbox.transliteration.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MrzChecksumGeneratorTest {

    private lateinit var underTest: MrzChecksumGenerator

    @BeforeEach
    fun setUp() {
        underTest = MrzChecksumGenerator(listOf(CroatianConverter(), CzechConverter(), GermanConverter(), SlovakConverter(), SpecialCharsConverter()))
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun digitToValue() {
        assertEquals(5, underTest.characterToValue('5'))
    }

    @Test
    fun lowerCaseLetterToValue() {
        assertEquals(11, underTest.characterToValue('b'))
    }

    @Test
    fun upperCaseLetterToValue() {
        assertEquals(26, underTest.characterToValue('Q'))
    }

    @Test
    fun lessThanToValue() {
        assertEquals(0, underTest.characterToValue('<'))
    }

    @Test
    fun unsupportedTo() {
        assertThrows(Exception::class.java, { underTest.characterToValue('>') })
    }

    @Test
    fun dateOfBirthToChecksum() {
        assertEquals(3, underTest.calculateChecksum("520727"))
    }

    @Test
    fun normalize() {
        assertEquals("ABCD89", underTest.normalize("abcd89", 6))
        assertEquals("ABCD89<<", underTest.normalize("abcd89", 8))
        assertThrows(Exception::class.java, { underTest.normalize("abcd898989", 8) })
    }
}
