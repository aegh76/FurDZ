package furhatos.app.furgui.flow.main


import RepeatQuestion
import No
import PatientNumber
import QRCode
import furhatos.app.furgui.flow.Parent
import furhatos.app.furgui.flow.helper.*
import furhatos.app.furgui.info.Informations
import furhatos.app.furgui.validation.validationNumberRO_TU
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.util.Language
import furhatos.skills.UserManager.current


// Hauptzustand "GreetingRO_TU", in dem Furhat den Benutzer begrüßt und nach einem QR-Code oder einer Patientennummer fragt, für die Sprache Türkisch und Rumänisch.
// Code in greeting.kt bereits Kommentiert
val GreetingRO_TU : State = state(Parent) {
    onEntry {


        furhatask(
            furhat = this.furhat,
            englishText = "Hello do you have a ${furhat.voice.emphasis("QR code")} " +
                    "Or do you have a patient number?",
            germanText = "Hallo Haben Sie einen ${furhat.voice.emphasis("QR-Code")} " +
                    "oder haben Sie eine Patientennummer?",
            turkishText = "Merhaba Bir ${furhat.voice.emphasis("kuh er kod")} " +
                    "ya da bir hasta numaranız var mı?",
            romanianText = "Bună ziua Aveți un ${furhat.voice.emphasis("QR code")} " +
                    "sau aveți un număr de pacient?",
            sprache = Benutzer!!.get("sprache") as Language

        )
    }

    onResponse<RepeatQuestion> {
        furhat.attend(user = current)
        reentry()
    }

    onResponse<PatientNumber> {
        furhat.attend(user = current)

        furhatsay(
            furhat = this.furhat,
            englishText = "Good, then I can help ${furhat.voice.emphasis("you")} further.",
            germanText = "Gut, dann kann ich ${furhat.voice.emphasis("Ihnen")} weiterhelfen.",
            turkishText = "Güzel, o zaman size daha fazla ${furhat.voice.emphasis("yardımcı")} olabilirim.",
            romanianText = "Bine, atunci pot să vă ajut  în continuare.",
            sprache = Benutzer!!.get("sprache") as Language
        )




        furhat.gesture(Gestures.BigSmile, async = false)



        Benutzer = users.getUser(it.userId)


        furhat.attend(user = current)


        patientnumberRO_TU(Benutzer!!, this.furhat)
        goto(validationNumberRO_TU)

    }






    onResponse<QRCode> {
        // Benutzerdaten aktualisieren
        Benutzer = users.getUser(it.userId)

        // Informationen über das Zeigen des QR-Codes geben
        furhatsay(
            furhat = this.furhat,
            englishText = "Okay, then please show me your QR code.",
            germanText = "Okay, dann zeige mir bitte Ihren QR-Code",
            turkishText = "Tamam, o zaman lütfen bana QR kodunuzu gösterin",
            romanianText = "Bine, atunci vă rog să-mi arătați codul QR",
            sprache = Benutzer!!.get("sprache") as Language
        )

        // Bildaufnahme vom Benutzer machen
        captureImageFromSocket(benutzer = Benutzer!!)
        Benutzer!!.put("Patientennummer", Benutzer!!.get("QR Code Text"))
        furhat.say("${Benutzer!!.get("Patientennummer")}")



        if (Benutzer!!.get("QR Code Text") != null) {


            //Eingaben fürs THM
            //       networkDrivePath = "smb://na-filer-w.thm.intern/daten/Projekte/Robotik/Furhat/projekte/Belegungsplan_Dialysezentrum/Furhat.csv",
            //       password = "FB07Tutor2022@THM", username = "07-Tutor")

            //Eingaben fürs DZ
           readExcel(Benutzer!!, this.furhat,
           networkDrivePath = "smb://10.203.31.70/mv/furhat/Furhat.csv",
           password = "Furhat2023", username = "Furhat")


            goto(Informations)

        } else {
            delay(8000) // Timer für 20 Sekunden
            goto(Idle)// Zurück in den Idle-Modus gehen
        }

    }
    onResponse<No>  {
        furhat.attend(user= current)

        furhatsay(
            furhat=this.furhat,
            englishText = "Then please report to the front desk Thank you.",
            germanText =  "Dann melden Sie sich bitte vorne beim Empfang Danke",
            turkishText = "O zaman lütfen resepsiyona gidin ve haber verin Teşekkür ederim",
            romanianText = "Atunci vă rugăm să vă prezentați la recepție Mulțumesc",
            sprache = Benutzer!!.get("sprache") as Language)

        furhat.gesture(Gestures.Nod())

        delay(2000)
        goto(Idle)
    }

    onResponse<RepeatQuestion> {
        furhat.attend(user= current)
        reentry()
    }

    onResponseFailed {
        furhatsay(furhat=this.furhat,
            englishText = "Sorry, i didn't understand that",
            germanText =  "Entschuldigung, ich habe Sie nicht verstanden",
            turkishText = "O zaman lütfen resepsiyona gidin ve haber verin Teşekkür ederim",
            romanianText = "Îmi pare rău, nu v-am înțeles.",
            sprache = Benutzer!!.get("sprache") as Language)

        reentry()
    }

    onUserLeave {
        furhatsay(
            furhat=this.furhat,
            englishText = "Goodbye",
            germanText =  "Tschüss",
            turkishText = "tammam",
            romanianText = "OK",
            sprache = Benutzer!!.get("sprache") as Language)
        goto(Idle)
    }

    onNoResponse {
        furhatsay(furhat=this.furhat,
            englishText = "Sorry, i didn't understand that",
            germanText =  "Entschuldigung, ich habe Sie nicht verstanden",
            turkishText = "O zaman lütfen resepsiyona gidin ve haber verin Teşekkür ederim",
            romanianText = "Îmi pare rău, nu v-am înțeles.",
            sprache = Benutzer!!.get("sprache") as Language)

        reentry()
    }

}



