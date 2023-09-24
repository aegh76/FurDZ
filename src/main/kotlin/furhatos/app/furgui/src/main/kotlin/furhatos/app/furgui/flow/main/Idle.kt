package furhatos.app.furgui.flow.main


import furhat.libraries.standard.UsersLib.usersLib
import furhatos.app.furgui.DataDelivery
import furhatos.app.furgui.flow.Parent
import furhatos.flow.kotlin.*
import furhatos.util.Gender
import furhatos.util.Language



// Der "Idle" Zustand repräsentiert den Ausgangszustand von Furhat, wenn er auf eine Benutzerinteraktion wartet.
val Idle: State = state(Parent) {
    //init Wird einmalig aufgerufen, wenn der Zustand (State) zum ersten Mal initialisiert wird.
    init {

        if(users.count > 0)
        {
            // Wenn sich Benutzer in der Nähe von Furhat befinden, leuchtet Furhats LED-Ring grün.
            furhat.ledStrip.solid(java.awt.Color.green)

            // Furhat versucht, den ihm am nächsten stehenden Benutzer zu beachten.
            furhat.usersLib.attendClosestUser()

            // Der Flow wird zum "Greetinglanguage" Zustand weitergeleitet.
            goto(Greetinglanguage)
        }
        // Wenn sich keine Benutzer in der Nähe befinden, richtet Furhat seine Aufmerksamkeit auf niemanden.
        else(furhat.attendNobody())
    }


    //onEntry Wird jedes Mal aufgerufen, wenn der Zustand betreten wird.
    onEntry {

        // Sendet ein Bild (in diesem Fall "50JahrePHV.jpg") an die Benutzeroberfläche.
        send(DataDelivery(imageName = "50JahrePHV.jpg"))

        // Furhat wird so eingestellt, dass er mehrere Sprachen (Englisch, Deutsch, Türkisch, Rumänisch) erkennen kann.
        furhat.setInputLanguage(Language.ENGLISH_US, Language.GERMAN, Language.TURKISH, Language.ROMANIAN)

        // Furhat wird mit einer weiblichen deutschen Stimme konfiguriert.
        furhat.setVoice(Language.GERMAN, Gender.FEMALE, false)


        if (users.count < 1) {
            // Wenn sich keine Benutzer in der Nähe befinden, richtet Furhat seine Aufmerksamkeit auf niemanden.
            furhat.attendNobody()
        } else {
            // Andernfalls richtet Furhat seine Aufmerksamkeit auf einen anderen Benutzer und wechselt zum "Greetinglanguage" Zustand.
            furhat.attend(users.other)
            goto(Greetinglanguage)
        }
    }

    // Wenn ein Benutzer in Furhats Sichtfeld tritt, richtet Furhat seine Aufmerksamkeit auf diesen Benutzer und wechselt zum "Greetinglanguage" Zustand.
    onUserEnter {
        furhat.attend(it)
        goto(Greetinglanguage)
    }

    // Bei erneutem Eintritt in diesen Zustand wird die gleiche Logik wie im "onEntry" Abschnitt angewendet.
    onReentry {
        furhat.attend(users.other)
        goto(Greetinglanguage)
    }

}

