import furhatos.nlu.Intent

// Funktioniert ähnlich wie die English Klasse, um Intentionen zu definieren.
class No : Intent() {

    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf(
            "Nein", "Nei", "Nain", "Fein", "rein", "sein", "ne", "nein auf gar keinen fall", "Nicht korrekt",
            "Falsch", "Das stimmt nicht so ganz", "Nein, ist falsch", "nope", "Falsch, das stimmt nicht", "Nö",
            "Maja", "Mara", "na", "naja", "na ja", "möchte ich nicht sagen",

            "Hayır", "hiçbir şekilde hayır", "Doğru değil",
            "Yanlış", "Bu pek doğru değil", "Hayır, yanlış", "Yanlış, doğru değil","yok", "hayır yok","bitirmeye geldim","getirmeye",

            "No",  "incorrect", "Not correct",
            "Wrong", "That's not right", "No, is wrong", "Wrong, that's not right","Nu", "Incorect",

            "Nu este corect",
            "Greșit", "Nu este corect", "Nu, este greșit", "Greșit, nu este corect"
        )
    }
}

