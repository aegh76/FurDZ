import furhatos.nlu.Intent

// Funktioniert ähnlich wie die English Klasse, um Intentionen zu definieren.
class Thanks : Intent() {
    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf(
            "Vielen Dank",
            "Danke, gleichfalls",
            "Danke",
            "Gleichfalls",
            "ebenfalls",
            "den wünsche ich auch",
            "Thank you very much.",
            "Thank you, likewise",
            "Thank you.",
            "likewise",
            "likewise",
            "thanks",
            "I wish you the same",
            "Çok teşekkür ederim",
            "Teşekkür ederim, aynı şekilde",
            "Teşekkür ederim.","teşekkür ederim",
            "Aynı şekilde",
            "Aynı şekilde",
            "Ben de bunu isterdim",
            "efendim",
            "Sağol",
            "Vă mulțumesc foarte mult",
            "Mulțumesc, la fel",
            "Mulțumesc",
            "la fel",
            "la fel",
            "Și eu aș vrea asta"
        )
    }
}



