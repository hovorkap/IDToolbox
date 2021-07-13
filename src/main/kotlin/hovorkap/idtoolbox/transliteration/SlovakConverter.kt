package hovorkap.idtoolbox.transliteration

import org.springframework.stereotype.Component

@Component
class SlovakConverter : TransliteratingConverter(
    mapOf(
        "Á" to "A",
        "Ä" to "A",
        "Č" to "C",
        "Ď" to "D",
        "É" to "E",
        "Ě" to "E",
        "Í" to "I",
        "Ĺ" to "L",
        "Ľ" to "L",
        "Ň" to "N",
        "Ó" to "O",
        "Ô" to "O",
        "Š" to "S",
        "Ŕ" to "R",
        "Ť" to "T",
        "Ú" to "U",
        "Ý" to "Y",
        "Ž" to "Z",

        "Ö" to "O",
        "Ü" to "U"
    )
)
