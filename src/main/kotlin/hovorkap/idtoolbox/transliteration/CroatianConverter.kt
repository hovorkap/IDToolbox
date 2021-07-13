package hovorkap.idtoolbox.transliteration

import org.springframework.stereotype.Component

@Component
class CroatianConverter : TransliteratingConverter(
    mapOf(
        "Ć" to "C",
        "Č" to "C",
        "Š" to "S",
        "Ž" to "Z",
        "Đ" to "D"
    )
)
