package furhatos.app.furgui.flow.main


import English
import German
import Romanian
import Turkish
import furhatos.app.furgui.flow.Parent
import furhatos.app.furgui.flow.helper.furhatsay
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.PollyVoice
import furhatos.gestures.Gestures
import furhatos.records.User
import furhatos.util.Language


// Ein Speicherort für Informationen über den aktuellen Benutzer.
// Wenn keine Informationen vorhanden sind, bleibt dieser Speicherort leer.
var Benutzer: User? = null

// Der Abschnitt "Greetinglanguage" definiert den Anfang des Gesprächs mit Furhat.
// Er baut auf dem allgemeinen Verhalten des "Parent" Abschnitts auf und fügt spezifische Aktionen für die Begrüßung hinzu.
val Greetinglanguage : State = state(Parent) {
    onEntry {

        // Den aktuellen Benutzer speichern
        Benutzer=users.current

        // Setzen von initialen Werten für den aktuellen Benutzer
        Benutzer!!.put("Patientennummer",null)
        Benutzer!!.put("QR Code Text",null)

        // Furhat wird angewiesen, den aktuellen Benutzer weiterhin anzuschauen
        furhat.attend(users.current)

        // Furhat begrüßt den Benutzer und fragt nach der bevorzugten Sprache. Furhat bietet vier Eingabesprachen an: Englisch, Deutsch, Türkisch und Rumänisch.
        // Die vom Benutzer gewählte Sprache wird im Verlauf des Gesprächs im Benutzerobjekt gespeichert
        furhat.ask("Hallo, ich bin Furhat. Ich kann Englisch, Deutsch, Türkisch und Rumänisch mit Ihnen sprechen. " +
                    "Sagen Sie mir einfach einen Satz auf welcher Sprache Sie fortführen möchten?")
        furhat.setInputLanguage(Language.ENGLISH_US, Language.GERMAN, Language.TURKISH, Language.ROMANIAN)

        // Furhat lächelt
        furhat.gesture(Gestures.BigSmile, async = false)

    }

    // Wenn der Benutzer auf Türkisch antwortet
    onResponse<Turkish> {
        // Den aktuellen Benutzer, der mit Furhat interagiert, in der Variable "Benutzer" speichern.
        Benutzer = users.current
        // Furhat's Stimme auf "Filiz" setzen, eine türkische Frauenstimme.
        furhat.voice = PollyVoice.Filiz()
        // Furhat's Eingabesprache auf Türkisch setzen, sodass er türkische Benutzereingaben erkennen kann.
        furhat.setInputLanguage(Language.TURKISH)
        // Die gewählte Sprache (in diesem Fall Türkisch) im Benutzerobjekt speichern, um sie später im Gesprächsverlauf wiederzuverwenden.
        Benutzer!!.put("sprache", Language.TURKISH)

        // Nachdem der Benutzer eine Sprache ausgewählt hat, verwendet Furhat die "furhatsay"-Hilfsfunktion, um eine Bestätigung in der gewählten Sprache auszugeben.
        // Die "furhatsay"-Funktion ermöglicht es Furhat, eine Nachricht in einer von vier Sprachen auszugeben. Die Sprache wird durch die Variable "sprache" bestimmt, die als Parameter übergeben wird.
        // Da Furhat in vier verschiedenen Sprachen (Englisch, Deutsch, Türkisch und Rumänisch) antworten kann, müssen alle vier Textoptionen bereitgestellt werden, damit die "furhatsay"-Funktion die richtige auswählen kann.
        furhatsay(
            furhat = this.furhat,
            germanText = "OK deutsch",
            englishText = "OK english",
            turkishText = "Tamam Türkçe",
            romanianText = "OK Română",
            sprache = Benutzer!!.get("sprache") as Language
        )

        // Leitet den Gesprächsfluss zu dem Zustand "GreetingRO_TU" weiter, um die Interaktion in der gewählten Sprache (hier Türkisch oder Rumänisch) fortzusetzen.
        goto(GreetingRO_TU)

    }

    // Wenn der Benutzer auf Englisch antwortet, wird die Interaktion entsprechend angepasst. Siehe Türkisch an.
    onResponse<English> {
        Benutzer = users.current
        furhat.voice = PollyVoice.Joanna()
        furhat.setInputLanguage(Language.ENGLISH_US)
        Benutzer!!.put("sprache", Language.ENGLISH_US)
        furhatsay(
            furhat = this.furhat,
            germanText = "OK deutsch",
            englishText = "OK english",
            turkishText = "Tamam Türkçe",
            romanianText = "OK Română",
            sprache = Benutzer!!.get("sprache") as Language
        )

        // Leitet den Gesprächsfluss zu dem Zustand "Greeting" weiter, um die Interaktion in der gewählten Sprache (hier Englisch oder Deutsch) fortzusetzen.
        goto(Greeting)

    }

    // Wenn der Benutzer auf Rumänisch antwortet, wird die Interaktion entsprechend angepasst. Siehe Türkisch an.
    onResponse<Romanian> {
        Benutzer = users.current
        furhat.voice = PollyVoice.Carmen()
        furhat.setInputLanguage(Language.ROMANIAN)
        Benutzer!!.put("sprache", Language.ROMANIAN)
        furhatsay(
            furhat = this.furhat,
            germanText = "OK deutsch",
            englishText = "OK english",
            turkishText = "Tamam Türkçe",
            romanianText = "OK Română",
            sprache = Benutzer!!.get("sprache") as Language
        )

        goto(GreetingRO_TU)

    }

    // Wenn der Benutzer auf Deutsch antwortet, wird die Interaktion entsprechend angepasst. Siehe Türkisch an.
    onResponse<German> {
        Benutzer = users.current
        furhat.voice = PollyVoice.Marlene()
        furhat.setInputLanguage(Language.GERMAN)
        Benutzer!!.put("sprache", Language.GERMAN)
        furhatsay(
            furhat = this.furhat,
            germanText = "OK deutsch",
            englishText = "OK english",
            turkishText = "Tamam Türkçe",
            romanianText = "OK Română",
            sprache = Benutzer!!.get("sprache") as Language
        )

        goto(Greeting)

    }

}


