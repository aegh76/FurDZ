package furhatos.app.furgui.flow.main


import FrageWiederholen
import Nein
import QRCode_Ja
import furhatos.app.furgui.flow.Parent
import furhatos.app.furgui.flow.helper.ReadExcel
import furhatos.app.furgui.flow.helper.captureImageFromSocket
import furhatos.app.furgui.flow.helper.furhatask
import furhatos.app.furgui.flow.helper.furhatsay
import furhatos.app.furgui.patient.Informationsdialogue
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.skills.UserManager.current
import furhatos.util.Language


val GreetingROeTU : State = state(Parent) {
    onEntry {


        furhatask(
            furhat = this.furhat,
            englishText = "Hello do you have a ${furhat.voice.emphasis("QR code")} " +
                    "Or do you have a patient number?",
            germanText = "Hallo Haben Sie einen ${furhat.voice.emphasis("QR-Code")} " +
                    "oder haben Sie eine Patientennummer?",
            turkishText = "Merhaba Bir ${furhat.voice.emphasis("kuh er kod")}?",
            romanianText = "Bună ziua Aveți un ${furhat.voice.emphasis("QR code")}?",
            sprache = Benutzer!!.get("sprache") as Language

        )
    }

    onResponse<FrageWiederholen> {
        furhat.attend(user = current)
        reentry()
    }

    onResponse<QRCode_Ja> {
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




        if (Benutzer!!.get("QR Code Text") != null) {

            //  captureImageFromSocket(benutzer = Benutzer!!)
            //   Benutzer!!.put("Patientennummer", Benutzer!!.get("QR Code Text"))
            furhat.say("${Benutzer!!.get("Patientennummer")}")
            ReadExcel(
                Benutzer!!, this.furhat,
                networkDrivePath = "smb://na-filer-w.thm.intern/daten/Projekte/Robotik/Furhat/projekte/Belegungsplan_Dialysezentrum/Furhat.csv",
                password = "FB07Tutor2022@THM", username = "07-Tutor"
            )

            //Eingaben fürs DZ
            //ReadExcel(Benutzer!!, this.furhat,
            //networkDrivePath = "smb://10.203.31.70/mv/furhat/Furhat.csv",
            //password = "Furhat2023", username = "Furhat")


            goto(Informationsdialogue)

        } else {
            delay(8000) // Timer für 20 Sekunden
            goto(Idle)// Zurück in den Idle-Modus gehen
        }

    }
    onResponse<Nein>  {
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

    onResponse<FrageWiederholen> {
        furhat.attend(user= current)
        reentry()
    }

    onResponseFailed {
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