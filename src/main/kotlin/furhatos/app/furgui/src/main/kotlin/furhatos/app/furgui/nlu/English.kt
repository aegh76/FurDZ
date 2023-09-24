import furhatos.nlu.Intent

// Die Klassen des Typs "Intent" definieren einzelne Intentionen. Wenn ein Benutzer beispielsweise "english" oder "englisch" sagt,
// stellen diese beiden Aussagen trotzdem die gleichen Intentionen dar.
// In Intents sammelt man also verschiedene Ausdrucksweisen, die man unter die gleiche Intention zusammenfassen kann.
// Das ermöglicht, dass Furhat die Intention "English" verstehen kann, obwohl der Gesprächspartner beispielsweise
// "Ich will englisch sprechen" gesagt hat.
class English : Intent() {

    // Überschreiben der Methode "getExamples", um eine Liste von Beispielsätzen zurückzugeben, die diese Intention repräsentieren.
    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf(
            "english",
            "englisch",
            "England",
            "English too",
            "In english please",
            "Englisch","I would like to continue in English",
            "Bitte in englisch fortfahren", "ich will englisch sprechen"
        )
    }

}