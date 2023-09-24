import furhatos.nlu.Intent

// Funktioniert ähnlich wie die English Klasse, um Intentionen zu definieren.
class Romanian : Intent() {

    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf(
            "Rumänisch", "rumänisch", "Rumänien"," Romana","limba romana", "romana", "romanesti","românește",
            "Română" , "în limba română vă rog", "vorbesc în limba română", "românesc, sunt românesc", "sunt român", "rumänisch", "Rumänisch",
            "Rumänien", "Ich möchte rumänisch sprechen", "rumänisch rumänisch", "brauchst du wohl besten Romane", "brauchst aber besten Romane", "brauchst aber bisschen Romana", "Hauser Worbis genommene"
        )
    }

}