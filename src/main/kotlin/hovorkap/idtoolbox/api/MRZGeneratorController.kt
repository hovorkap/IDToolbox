package hovorkap.idtoolbox.api

import hovorkap.idtoolbox.MrzChecksumGenerator
import hovorkap.idtoolbox.api.MRZGeneratorController.Companion.PATH
import io.swagger.v3.oas.annotations.Operation
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
        const val PATH = "/api/idgen/td1"
    }

    @Operation(summary = "Generate MRZ")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun generateMrz(
            @Parameter(required = false) dateOfBirth: String? = "",
            @Parameter(required = false) documentCode: String? = "ID",
            @Parameter(required = false) documentNumber: String? = "",
            @Parameter(required = false) countryCode: String? = "SVK",
            @Parameter(required = false) surname: String? = "",
            @Parameter(required = false) givenNames: String? = "",
            @Parameter(required = false) nationality: String? = "SVK",
            @Parameter(required = false) personalNumber: String? = "",
            @Parameter(required = false) sex: String? = "",
            @Parameter(required = false) dateOfExpiry: String? = "",
            @Parameter(required = false) optionalData: String? = ""

    ): List<String> {
        val c = checksumGenerator

        val line1 =
                c.normalize(documentCode ?: "", 2) +
                        c.normalize(countryCode ?: "", 3) +
                        c.normalize(documentNumber ?: "", 9) +
                        c.calculateChecksum(documentNumber ?: "") +
                        c.normalize(personalNumber ?: "", 15)

        val line2 = (c.normalize(dateOfBirth ?: "", 6) +
                c.calculateChecksum(dateOfBirth ?: "") +
                c.normalize(sex ?: "", 1) +
                c.normalize(dateOfExpiry ?: "", 6) +
                c.calculateChecksum(dateOfExpiry ?: "") +
                c.normalize(nationality ?: "", 3) +
                c.normalize(optionalData ?: "", 11)).let { it + c.calculateChecksum(line1, it) }

        val line3 = c.normalize(c.normalize(surname ?: "", (surname ?: "").length + 2) +
                c.normalize(givenNames ?: "", (givenNames ?: "").length), 30)




        return listOf(line1, line2, line3)

    }

}