import furhatos.nlu.Intent

// Funktioniert ähnlich wie die English Klasse, um Intentionen zu definieren.
class Turkish : Intent() {

    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf(
            "turkish", "ich will auf Türkisch fortfahren", "ich möchte Türkisch sprechen",
            "türkisch",
            "Türkei",
            "Auch Türkisch", "Türkçe","Türkçe konuş benimle","Benimle Türkçe konuş","Türkçe konuş Benim de", "Benimle Türkçe konuşur musun",
        "turkish turkish"
        )
    }

}