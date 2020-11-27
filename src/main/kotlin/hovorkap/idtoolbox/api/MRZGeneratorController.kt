package hovorkap.idtoolbox.api

import hovorkap.idtoolbox.MrzChecksumGenerator
import hovorkap.idtoolbox.api.MRZGeneratorController.Companion.PATH
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "IDGenerator")
@RestController
@RequestMapping(value = [PATH])
class MRZGeneratorController(val checksumGenerator: MrzChecksumGenerator) {

    companion object {
        const val PATH = "/api/idgen"
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun generateMrz(
            @Parameter(required = true) dateOfBirth: String = "",
            @Parameter(required = true) documentCode: String = "ID",
            @Parameter(required = true) documentNumber: String = "",
            @Parameter(required = true) countryCode: String = "SVK",
            @Parameter(required = true) surname: String = "",
            @Parameter(required = true) givenNames: String = "",
            @Parameter(required = true) nationality: String = "SVK",
            @Parameter(required = true) personalNumber: String = "",
            @Parameter(required = true) sex: String = "",
            @Parameter(required = true) dateOfExpiry: String = ""

    ): List<String> {
        val c = checksumGenerator

        val line1 =
                c.normalize(documentCode, 2) +
                        c.normalize(countryCode, 3) +
                        c.normalize(documentNumber, 9) +
                        c.calculateChecksum(documentNumber) +
                        c.normalize("", 15)

        val line2 = (c.normalize(dateOfBirth, 6) +
                c.calculateChecksum(dateOfBirth) +
                c.normalize(sex, 1) +
                c.normalize(dateOfExpiry, 6) +
                c.calculateChecksum(dateOfExpiry) +
                c.normalize(nationality, 3) +
                c.normalize("", 11)).let { it + c.calculateChecksum(line1, it) }

        val line3 = c.normalize(c.normalize(surname, surname.length + 2) +
                c.normalize(givenNames, givenNames.length), 30)




        return listOf(line1, line2, line3)

    }

}