package furhatos.app.furgui.validation


import RepeatQuestion
import No
import Yes
import furhatos.app.furgui.flow.Parent
import furhatos.app.furgui.flow.helper.*
import furhatos.app.furgui.flow.main.Benutzer
import furhatos.app.furgui.flow.main.Idle
import furhatos.app.furgui.info.Informations
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice
import furhatos.skills.UserManager.current
import furhatos.util.Language

// Der State "validationNumber" dient dazu, die vom Furhat erkannte Patientennummer zu validieren. Für die Sprachen Englisch und Deutsch.
val validationNumber : State = state(Parent) {

    onEntry {
        furhat.attend(users.current)
        // Die Patientennummer des Benutzers wird aus dem Benutzerobjekt geholt.
        // Die Patientennummer wird in einzelne Ziffern zerlegt, um sie besser aussprechen zu können.
        val patientennummer = Benutzer!!.get("Patientennummer").toString()
        val patientennummerAsDigits = furhat.voice.sayAs(patientennummer, Voice.SayAsType.DIGITS)

        // Furhat fragt den Benutzer, ob die erkannte Patientennummer korrekt ist.
        furhatask(furhat=this.furhat,
            englishText = "The patient number is ${patientennummerAsDigits}, is that correct?",
            germanText =  "Die Patientennummer ist ${patientennummerAsDigits}, stimmt das?",
            turkishText = "Hasta numarası ${patientennummerAsDigits}, doğru mu?",
            romanianText = "Numărul pacientului este ${patientennummerAsDigits}, este corect?",
            sprache = Benutzer!!.get("sprache") as Language)



    }


    // Wenn der Benutzer bestätigt, dass die Patientennummer korrekt ist, wird die CSV-Datei gelesen.
    onResponse<Yes> {

     readExcel(Benutzer!!, this.furhat,
         //  Eingaben fürs THM
         /*  networkDrivePath = "smb://na-filer-w.thm.intern/daten/Projekte/Robotik/Furhat/projekte/Belegungsplan_Dialysezentrum/Furhat.csv",
           password = "FB07Tutor2022@THM", username = "07-Tutor")*/

    //  Eingaben fürs DZ
        networkDrivePath = "smb://10.203.31.70/mv/furhat/Furhat.csv",
        password = "Furhat2023", username = "Furhat")



    // Überprüfung, ob der Benutzer einen Termin hat.
    // Wenn der Benutzer keinen Termin hat, wird er darüber informiert.
    if (Benutzer!!.get("raum") == null) {

        furhatsay(furhat=this.furhat,
            englishText = "Unfortunately you are not on the occupancy plan which I have, please ask at the" +
                    " reception. I wish you a nice day",
            germanText =  "Sie stehen leider nicht auf dem Belegungsplan welcher mir vorliegt, bitte fragen Sie am" +
                    " Empfang nach. Ich wünsche Ihnen einen schönen Tag",
            turkishText = "Maalesef, elimdeki doluluk planında yazmıyorsunuz. Lütfen Resepsiyona sorun" +
                    ". İyi günler dilerim",
            romanianText = "Din păcate, nu vă aflați pe planul de ocupare pe care îl am, vă rog să întrebați la" +
                    "la recepție. Vă doresc o zi bună",
            sprache = Benutzer!!.get("sprache") as Language)


        delay(1500)



       goto(Idle)




    }
    // Wenn der Benutzer einen Termin hat, wird er zu weiteren "Informations" weitergeleitet.
    else goto(Informations)

    }

    // Wenn der Benutzer angibt, dass die Patientennummer nicht korrekt ist, wird die Patientennummer zurückgesetzt und erneut abgefragt.
    onResponse<No> {
        furhat.attend(user= current)
        Benutzer!!.put("Patientennummer", null)
        getDigits(Benutzer!!, this.furhat)

     reentry()

    }

    // Wenn der Benutzer die Frage wiederholen möchte, wird der State erneut betreten.
    onResponse<RepeatQuestion> {
    furhat.attend(user= current)
    reentry()
    }

    // Wenn keine Antwort vom Benutzer kommt, wird er darüber informiert, dass Furhat ihn nicht verstanden hat und der State wird erneut betreten.
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




