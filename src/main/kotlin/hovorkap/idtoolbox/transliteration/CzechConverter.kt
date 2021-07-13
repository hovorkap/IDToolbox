package hovorkap.idtoolbox.transliteration

import org.springframework.stereotype.Component

@Component
class CzechConverter : TransliteratingConverter(
    mapOf(
        "Á" to "A",
        "Č" to "C",
        "Ď" to "D",
        "É" to "E",
        "Ě" to "E",
        "Í" to "I",
        "Ň" to "N",
        "Ó" to "O",
        "Ř" to "R",
        "Š" to "S",
        "Ť" to "T",
        "Ú" to "U",
        "Ů" to "U",
        "Ý" to "Y",
        "Ž" to "Z",

        "Ä" to "A",
        "Ö" to "O",
        "Ü" to "U"
    )
)
