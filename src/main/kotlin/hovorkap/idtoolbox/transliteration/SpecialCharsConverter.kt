package hovorkap.idtoolbox.transliteration

import org.springframework.stereotype.Component

@Component
class SpecialCharsConverter : TransliteratingConverter(
    mapOf(
        "-" to " "
    )
)
