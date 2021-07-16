package hovorkap.idtoolbox.api

import hovorkap.idtoolbox.MrzChecksumGenerator
import hovorkap.idtoolbox.transliteration.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MRZGeneratorControllerTest {

    private lateinit var underTest: MRZGeneratorController

    @BeforeEach
    fun setUp() {
        underTest = MRZGeneratorController(MrzChecksumGenerator(listOf(CroatianConverter(), CzechConverter(), GermanConverter(), SlovakConverter(), SpecialCharsConverter())))
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun generateMrz() {
        val result = underTest.generateMrz(
                documentCode = "I",
                countryCode = "UTO",
                documentNumber = "D23145890",
                dateOfBirth = "740812",
                sex = "F",
                dateOfExpiry = "120415",
                nationality = "UTO",
                surname = "Eriksson",
                givenNames = "Anna Maria"
        )
        assertEquals("I<UTOD231458907<<<<<<<<<<<<<<<", result.get(0))
        assertEquals("7408122F1204159UTO<<<<<<<<<<<6", result.get(1))
        assertEquals("ERIKSSON<<ANNA<MARIA<<<<<<<<<<", result.get(2))
    }

    @Test
    fun generateMrzHrv() {
        val result = underTest.generateMrz(
                documentCode = "IO",
                countryCode = "HRV",
                documentNumber = "000000000",
                dateOfBirth = "770101",
                sex = "F",
                dateOfExpiry = "021212",
                nationality = "HRV",
                surname = "Specimen",
                givenNames = "Specimen"
        )
        assertEquals("IOHRV0000000000<<<<<<<<<<<<<<<", result.get(0))
        assertEquals("7701018F0212126HRV<<<<<<<<<<<0", result.get(1))
        assertEquals("SPECIMEN<<SPECIMEN<<<<<<<<<<<<", result.get(2))
    }

    @Test
    fun generatePermanentSkIdMrzL1() {
        val result = `generateSNCU-3408-2`()
        assertEquals("IDSVKEA782543<9461103775<<<<<<", result.get(0))
    }

    @Test
    fun generatePermanentSkIdMrzL2() {
        val result = `generateSNCU-3408-2`()
        assertEquals("4611037M<<<<<<0SVK<<<<<<<<<<<4", result.get(1))
    }

    @Test
    fun generatePermanentSkIdMrzL3() {
        val result = `generateSNCU-3408-2`()
       assertEquals("KOVACIK<<METOD<<<<<<<<<<<<<<<<", result.get(2))
    }

    //Numbers are changed due to confidentality
    private fun `generateSNCU-3408-2`(): List<String> {
        return underTest.generateMrz(
            documentCode = "ID",
            countryCode = "SVK",
            documentNumber = "EA782543",
            dateOfBirth = "461103",
            sex = "M",
            dateOfExpiry = "",
            nationality = "SVK",
            surname = "Kováčik",
            givenNames = "Metod",
            personalNumber = "461103775"
        )
    }


    @Test
    fun dateOfBirthIsInValid() {
        Assertions.assertThrows(Exception::class.java) { underTest.generateMrz(dateOfBirth = "000000") }
    }

    @Test
    fun dateOfExpiryIsInValid() {
        Assertions.assertThrows(Exception::class.java) { underTest.generateMrz(dateOfExpiry = "000000") }
    }
}
