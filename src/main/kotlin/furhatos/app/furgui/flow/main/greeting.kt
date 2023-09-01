package furhatos.app.furgui.flow.main


import FrageWiederholen
import Nein
import Patientennummer
import QRCode
import furhatos.app.furgui.flow.Parent
import furhatos.app.furgui.flow.helper.*
import furhatos.app.furgui.patient.Informationsdialogue
import furhatos.app.furgui.taxidriver.BringenOderAbholen
import furhatos.app.furgui.taxidriver.ValidierungNummerKunde
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.skills.UserManager.current
import furhatos.util.Language





val Greeting : State = state(Parent) {
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

    onResponse<Patientennummer> {
        furhat.attend(user = current)

        furhatsay(
            furhat = this.furhat,
            englishText = "Good, then I can help ${furhat.voice.emphasis("you")} further.",
            germanText = "Gut, dann kann ich ${furhat.voice.emphasis("Ihnen")} weiterhelfen.",
            turkishText = "Güzel, o zaman size daha fazla ${furhat.voice.emphasis("yardımcı")} olabilirim.",
            romanianText = "Bine, atunci pot să vă ajut  în continuare.",
            sprache = Benutzer!!.get("sprache") as Language
        )


        //mit ${furhat.voice.emphasis("Ihnen")} kann Furhat einzelne Abschnitte betonen.

        furhat.gesture(Gestures.BigSmile, async = false)


        //Nun wird die Variable Benutzer mit dem User überschrieben, der auf dei Frage geantwortet hat.
        Benutzer = users.getUser(it.userId)

        //Der Benutzer wird von Furhat angeschaut.
        furhat.attend(user = current)

        //goto(NoGUI) klappt

        //Mit der Funktion GetDigitsPatient wird die Frage nach der Patientennummer des Gesprächspartners gestellt.
        //User kann nicht mehr null sein deswegen Benutzer!!, da der Benutzer bereits gesetzt.
       GetDigitsKunde(Benutzer!!, this.furhat)
      goto(ValidierungNummerKunde)

    }


    onResponse<QRCode> {
        Benutzer = users.getUser(it.userId)





        furhatsay(
            furhat = this.furhat,
            englishText = "Okay, then please show me your QR code.",
            germanText = "Okay, dann zeigen Sie mir bitte ihren QR-Code",
            turkishText = "Tamam, o zaman lütfen bana QR kodunuzu gösterin",
            romanianText = "Bine, atunci vă rog să-mi arătați codul QR",
            sprache = Benutzer!!.get("sprache") as Language
        )


        captureImageFromSocket(benutzer = Benutzer!!)
        Benutzer!!.put("Patientennummer", Benutzer!!.get("QR Code Text"))




        if (Benutzer!!.get("QR Code Text") != null) {

            //  captureImageFromSocket(benutzer = Benutzer!!)
            //   Benutzer!!.put("Patientennummer", Benutzer!!.get("QR Code Text"))
            furhat.say("${Benutzer!!.get("Patientennummer")}")

            //ReadExcel(Benutzer!!, this.furhat,
            //networkDrivePath = "smb://10.203.31.70/mv/furhat/Furhat.csv",
            //password = "Furhat2023", username = "Furhat")


            ReadExcel(
                Benutzer!!, this.furhat,
                networkDrivePath = "smb://na-filer-w.thm.intern/daten/Projekte/Robotik/Furhat/projekte/Belegungsplan_Dialysezentrum/Furhat.csv",
                password = "FB07Tutor2022@THM", username = "07-Tutor"
            )


            //Eingaben fürs DZ

            goto(Informationsdialogue)

        } else {
            delay(8000)
            goto(Idle)
        }

    }


    onResponse<Nein>  {
        furhat.attend(user= current)
        goto(BringenOderAbholen)
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
            turkishText = "tamam",
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
