package hovorkap.idtoolbox.api

import hovorkap.idtoolbox.MrzChecksumGenerator
import hovorkap.idtoolbox.transliteration.CroatianConverter
import hovorkap.idtoolbox.transliteration.CzechConverter
import hovorkap.idtoolbox.transliteration.GermanConverter
import hovorkap.idtoolbox.transliteration.SlovakConverter
import hovorkap.idtoolbox.transliteration.SpecialCharsConverter
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Trajno {

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
        val result = underTest.generateTD1(
                documentCode = "IO",
                countryCode = "HRV",
                documentNumber = "107348243",
                dateOfBirth = "851209",
                sex = "M",
                dateOfExpiry = "991231",
                nationality = "HRV",
                surname = "ŠLJIVAR",
                givenNames = "DAMIR",
            personalNumber = ""
        )
        assertEquals("IOHRV1073482434<<<<<<<<<<<<<<<", result.get(0))
        assertEquals("8512095M9912315HRV<<<<<<<<<<<8", result.get(1))
        assertEquals("SLJIVAR<<DAMIR<<<<<<<<<<<<<<<<", result.get(2))
    }
}
