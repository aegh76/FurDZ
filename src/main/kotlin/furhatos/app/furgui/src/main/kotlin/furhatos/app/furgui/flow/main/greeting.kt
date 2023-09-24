package furhatos.app.furgui.flow.main


import RepeatQuestion
import No
import PatientNumber
import QRCode
import furhatos.app.furgui.flow.Parent
import furhatos.app.furgui.flow.helper.*
import furhatos.app.furgui.info.Informations
import furhatos.app.furgui.validation.validationNumber
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.skills.UserManager.current
import furhatos.util.Language




// Hauptzustand "Greeting", in dem Furhat den Benutzer begrüßt und nach einem QR-Code oder einer Patientennummer fragt, für die Sprache Englisch und Deutsch.
val Greeting : State = state(Parent) {
    onEntry {

        // Furhat fragt den Benutzer, ob er einen QR-Code oder eine Patientennummer hat.
        // Die Frage wird in der zuvor festgelegten Sprache gestellt.
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
    // Wenn der Benutzer angibt, dass er eine Patientennummer hat oder ein Wort aus der "PatientNumber" Kotlin-Klasse verwendet, wird dieser Block ausgeführt.
    onResponse<PatientNumber> {
        //Furhat richtet erneut seine Aufmerksamkeit auf den aktuellen Benutzer.
        furhat.attend(user = current)

        // Furhat bestätigt die Eingabe und sagt, dass er weiterhelfen kann. Mit ${furhat.voice.emphasis("Ihnen")} kann Furhat einzelne Abschnitte betonen.
        furhatsay(
            furhat = this.furhat,
            englishText = "Good, then I can help ${furhat.voice.emphasis("you")} further.",
            germanText = "Gut, dann kann ich ${furhat.voice.emphasis("Ihnen")} weiterhelfen.",
            turkishText = "Güzel, o zaman size daha fazla ${furhat.voice.emphasis("yardımcı")} olabilirim.",
            romanianText = "Bine, atunci pot să vă ajut  în continuare.",
            sprache = Benutzer!!.get("sprache") as Language
        )

        furhat.gesture(Gestures.BigSmile, async = false)


        // Hier wird das aktuelle Benutzerobjekt, das auf die letzte Interaktion reagiert hat, aus dem "users"-Speicher abgerufen.
        // Das "it.userId" gibt die eindeutige ID des Benutzers zurück, der gerade geantwortet hat.
        // Das Ergebnis dieser Abfrage wird in der Variable "Benutzer" gespeichert, sodass Sie auf die Daten dieses spezifischen Benutzers zugreifen können.
        Benutzer = users.getUser(it.userId)

        //Der Benutzer wird von Furhat angeschaut.
        furhat.attend(user = current)



        // Die Funktion "getDigits" stellt dem Gesprächspartner die Frage nach seiner Patientennummer.
        // Da der Benutzer zu diesem Zeitpunkt bereits festgelegt wurde, kann er nicht null sein.
        // Deshalb wird "Benutzer!!" verwendet, um sicherzustellen, dass der Benutzer existiert.
        getDigits(Benutzer!!, this.furhat)
        goto(validationNumber)

    }

    // Wenn der Benutzer angibt, dass er einen QRCode hat oder ein Wort aus der "QRCode" Kotlin-Klasse verwendet, wird dieser Block ausgeführt.
    onResponse<QRCode> {
        Benutzer = users.getUser(it.userId)

        // Furhat bittet den Benutzer, den QR-Code zu zeigen und liest ihn ein.
        furhatsay(
            furhat = this.furhat,
            englishText = "Okay, then please show me your QR code.",
            germanText = "Okay, dann zeigen Sie mir bitte ihren QR-Code",
            turkishText = "Tamam, o zaman lütfen bana QR kodunuzu gösterin",
            romanianText = "Bine, atunci vă rog să-mi arătați codul QR",
            sprache = Benutzer!!.get("sprache") as Language
        )

        // Die Funktion "captureImageFromSocket" wird aufgerufen, um ein Bild einen QR-Code über einen Socket zu erfassen. Der aktuelle Benutzer wird als Parameter übergeben.
        captureImageFromSocket(benutzer = Benutzer!!)
        // Nachdem das Bild erfasst wurde, wird der erkannte Text des QR-Codes (falls vorhanden)
        // aus dem Benutzerobjekt abgerufen und als "Patientennummer" im selben Benutzerobjekt gespeichert.
        Benutzer!!.put("Patientennummer", Benutzer!!.get("QR Code Text"))
        // Furhat sagt den erkannten Text des QR-Codes an.
        furhat.say("${Benutzer!!.get("Patientennummer")}")



        if (Benutzer!!.get("QR Code Text") != null) {


            // Lese Daten aus einer CSV-Datei, die auf einem Netzlaufwerk gespeichert ist. Der Furhat hat dabei den Pfad, den Benutzernamen und einen Passwort um aufs Verzeichnis zuzugreifen.
            readExcel(Benutzer!!, this.furhat,
                //Eingaben fürs DZ
            networkDrivePath = "smb://10.203.31.70/mv/furhat/Furhat.csv",
            password = "Furhat2023", username = "Furhat")


          /*    Eingaben fürs THM
                networkDrivePath = "smb://na-filer-w.thm.intern/daten/Projekte/Robotik/Furhat/projekte/Belegungsplan_Dialysezentrum/Furhat.csv",
                password = "FB07Tutor2022@THM", username = "07-Tutor")*/


            goto(Informations)

        } else {
            // Wenn kein QR-Code erkannt wurde, warte 8 Sekunden.
            delay(8000)
            // Gehe zurück zum Anfangsstatus.
            goto(Idle)
        }

    }

    // Wenn der Benutzer Nein oder ein Wort aus der "No" Kotlin-Klasse verwendet, wird dieser Block ausgeführt.
    onResponse<No>  {
        furhat.attend(user= current)
        furhatsay(
            furhat=this.furhat,
            englishText = "Then please report to the front desk" + this.furhat.gesture(Gestures.Nod) + "Thank you.",
            germanText =  "Dann melden Sie sich bitte vorne beim Empfang" + this.furhat.gesture(Gestures.Nod) + "Danke",
            turkishText = "O zaman lütfen resepsiyona gidin ve haber verin" + this.furhat.gesture(Gestures.Nod) + "Teşekkür ederim",
            romanianText = "Atunci vă rugăm să vă prezentați la recepție" + this.furhat.gesture(Gestures.Nod) + "Mulțumesc",
            sprache = Benutzer!!.get("sprache") as Language)

        delay(2000)
        goto(Idle)

    }

    // Wenn der Benutzer ein Wort aus der "RepeatQuestion" Kotlin-Klasse verwendet, wird dieser Block ausgeführt.
    onResponse<RepeatQuestion> {
        furhat.attend(user= current)
        reentry()
    }

    // Wenn Furhat die Antwort des Benutzers nicht versteht.
    onResponseFailed {
        reentry()
    }
    // Wenn der Benutzer die Interaktion verlässt
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
    // Wenn Furhat keine Antwort vom Benutzer erhält.
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
