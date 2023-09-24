import furhatos.nlu.Intent

// Funktioniert ähnlich wie die English Klasse, um Intentionen zu definieren.
class German : Intent() {
    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf(
            "Deutsch",
            "Ich bin deutscher",
            "In Deutsch bitte",
            "deutsch","Ich würde gerne auf Deutsch fortfahren"
        )
    }
}



