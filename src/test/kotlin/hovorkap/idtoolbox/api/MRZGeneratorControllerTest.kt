package hovorkap.idtoolbox.api

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MRZGeneratorControllerTest {

    private lateinit var underTest: MRZGeneratorController

    @BeforeEach
    fun setUp() {
        underTest = MRZGeneratorController(MrzChecksumGenerator())
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
}