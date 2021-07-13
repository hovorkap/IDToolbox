package hovorkap.idtoolbox.transliteration

import org.springframework.stereotype.Component

@Component
class UpperCaseConverter : MachineReadableZoneConverter {
    override fun convert(str: String): String = str.toUpperCase()
}
