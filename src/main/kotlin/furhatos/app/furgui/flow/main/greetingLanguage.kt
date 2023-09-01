package furhatos.app.furgui.flow.main

import Englisch
import Rumaenisch
import Tuerkisch
import furhatos.app.furgui.flow.Parent
import furhatos.app.demo02.nluAlt.Deutsch
import furhatos.app.furgui.NoGUI
import furhatos.app.furgui.flow.helper.ReadExcel
import furhatos.app.furgui.flow.helper.furhatsay
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.PollyVoice
import furhatos.gestures.Gestures
import furhatos.records.User
import furhatos.util.Language



var Benutzer: User? = null

//Der State Greeting erbt von dem State Parent, dort ist das User-Handling definiert.
val Greetinglanguage : State = state(Parent) {
    onEntry {
        Benutzer=users.current

        Benutzer!!.put("Patientennummer",null)

        Benutzer!!.put("QR Code Text",null)



        //Zu Beginn des States wird definiert, dass Furhat den aktuellen User weiterhin anschaut.
        //furhat.attend(users.current)
        furhat.attend(users.current)


        furhat.ask(
            "Hallo, ich bin Furhat. Ich kann Englisch, Deutsch, Türkisch und Rumänisch mit Ihnen sprechen. Sagen Sie mir einfach einen Satz auf welcher Sprache Sie fortführen möchten?"
        )
        furhat.setInputLanguage(Language.ENGLISH_US, Language.GERMAN, Language.TURKISH, Language.ROMANIAN)

        furhat.gesture(Gestures.BigSmile, async = false)

    }


    onResponse<Tuerkisch> {
        Benutzer = users.current
        furhat.voice = PollyVoice.Filiz()
        furhat.setInputLanguage(Language.TURKISH)
        Benutzer!!.put("sprache", Language.TURKISH)
        furhatsay(
            furhat = this.furhat,
            germanText = "OK deutsch",
            englishText = "OK english",
            turkishText = "Tamam Türkçe",
            romanianText = "OK Română",
            sprache = Benutzer!!.get("sprache") as Language
        )
        goto(GreetingROeTU)
    }
    onResponse<Englisch> {
        Benutzer = users.current
        furhat.voice = PollyVoice.Joey()
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
        goto(Greeting)
    }

    onResponse<Rumaenisch> {
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
        goto(GreetingROeTU)
    }

    onResponse<Deutsch> {
        Benutzer = users.current
        furhat.voice = PollyVoice.Hans()
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


