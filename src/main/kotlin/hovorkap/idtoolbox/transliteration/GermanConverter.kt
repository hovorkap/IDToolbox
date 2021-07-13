package hovorkap.idtoolbox.transliteration

import org.springframework.stereotype.Component

@Component
class GermanConverter : TransliteratingConverter(
    mapOf(
        "Ä" to "AE",
        "Ö" to "OE",
        "Ü" to "UE",
        "ß" to "SS"
    )
)
