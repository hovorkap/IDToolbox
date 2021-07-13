package hovorkap.idtoolbox.transliteration

open class TransliteratingConverter(private val transliterations: Map<String, String>) : MachineReadableZoneConverter {
    override fun convert(str: String): String {
        var result = str
        transliterations.forEach { oldChar, newChar -> result = result.replace(oldChar, newChar) }
        return result
    }
}
