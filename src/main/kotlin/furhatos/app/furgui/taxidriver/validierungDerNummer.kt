package furhatos.app.furgui.taxidriver


import FrageWiederholen
import Nein
import furhatos.app.furgui.NoGUI
import furhatos.app.furgui.flow.Parent
import furhatos.app.furgui.flow.helper.ReadExcel
import furhatos.app.furgui.flow.helper.furhatask
import furhatos.app.furgui.flow.helper.furhatsay
import furhatos.app.furgui.flow.main.Benutzer
import furhatos.app.furgui.flow.main.Idle
import furhatos.app.furgui.patient.Informationsdialogue
import nlu.Ja
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice
import furhatos.skills.UserManager.current
import furhatos.util.Language

val ValidierungNummerKunde : State = state(Parent) {

    onEntry {
        furhat.attend(users.current)

        val patientennummer = Benutzer!!.get("Patientennummer").toString()
        val patientennummerAsDigits = furhat.voice.sayAs(patientennummer, Voice.SayAsType.DIGITS)

        furhatask(
            furhat=this.furhat,
            englishText = "The patient number is ${patientennummerAsDigits}, is that correct?",
            germanText =  "Die Patientennummer ist ${patientennummerAsDigits}, stimmt das?",
            turkishText = "Hasta numarası ${patientennummerAsDigits}, doğru mu?",
            romanianText = "Numărul pacientului este ${patientennummerAsDigits}, este corect?",
            sprache = Benutzer!!.get("sprache") as Language

        )



        /*/Hier fragt Furhat den gesprächspartner, ob seine verstandene Patientennummer korrekt ist.
        furhat.ask {
            +"Die Patientennummer ist"
            //Durch "Voice.SayAsType.DIGITS" spricht Furhat dabei die Patientennummer als 5 separate Ziffern.
            +voice!!.sayAs("${Benutzer!!.get("Patientennummer")}", Voice.SayAsType.DIGITS)
            +", stimmt das?"
            furhat.gesture(Gestures.BigSmile)
        }*/

    }

    onResponse<Ja> {
       // goto(NoGUI) klappt

   //Die Funktion ReadExcel wird aufgerufen und auf den Benutzer angewendet.
   ReadExcel(Benutzer!!, this.furhat,
       networkDrivePath = "smb://na-filer-w.thm.intern/daten/Projekte/Robotik/Furhat/projekte/Belegungsplan_Dialysezentrum/Furhat.csv",
       password = "FB07Tutor2022@THM", username = "07-Tutor")

   /*  Eingaben fürs DZ
       ReadExcel(Benutzer!!, this.furhat,
       networkDrivePath = "smb://10.203.31.70/mv/furhat/Furhat.csv",
       password = "Furhat2023", username = "Furhat")*/



   //Hat der Benutzer keinen Termin, wird das field "Kein Termin" des Benutzers mit -1 beschrieben und die If-Bedingung
   //trifft zu. Sollte der Benutzer keinen Termin haben wird er darüber informiert, die Interaktion endet dann.
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
   //Ist das field "row" nicht -1, so läuft die interaktion weiter mit dem State "Patientdialogue".
   else goto(Informationsdialogue)
        //goto(NoGUI) klappt
}

onResponse<Nein> {
   furhat.attend(user= current)

   //Ist die Patientennummer falsch, so wird im State WennNummerFalsch das field patientennummer genullt und
   //Furhat fragt erneut nach der Patientennummer.
   goto(WennNummerFalschPatient)
}
onResponse<FrageWiederholen> {
   furhat.attend(user= current)
   reentry()
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




