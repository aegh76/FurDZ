package furhatos.app.furgui.info


import furhatos.app.furgui.NoGUI
import furhatos.app.furgui.flow.Parent
import furhatos.app.furgui.flow.helper.furhatsay
import furhatos.app.furgui.flow.main.Benutzer
import furhatos.app.furgui.flow.main.Idle
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice
import furhatos.gestures.Gestures
import furhatos.util.Language

// Der Zustand "Informations" dient dazu, dem Benutzer Informationen über die Dialyse zu geben.
val Informations : State = state(Parent) {

    onEntry {


        // Extrahieren des Raums aus dem Benutzerobjekt und Speichern in der Variable "raumy".
        // Extrahieren die Bettnummer aus dem Benutzerobjekt und Speichern in der Variable "platzx".
        val raumy: Any = Benutzer!!.get("raum")
        val platzx: Any = Benutzer!!.get("platz")
        // Extrahieren der Startzeit und der Endzeit der Dialyse aus dem Benutzerobjekt. Die Methode "furhat.voice.sayAs" wird verwendet,
        // um die Zeit in einem für die Sprachausgabe geeigneten Format zu konvertieren. "Voice.SayAsType.TIME" gibt an,
        // dass der übergebene String als Zeit interpretiert werden soll.
        val dialysebeginn: Any = furhat.voice.sayAs(Benutzer!!.get("dialysebeginn").toString(), Voice.SayAsType.TIME)
        val dialyseende: Any = furhat.voice.sayAs(Benutzer!!.get("dialyseende").toString(), Voice.SayAsType.TIME)



        // Der Nutzer wird über seine Termindaten informiert. Dieser Abschnitt gibt dem Nutzer Auskunft darüber,
        // wann, wo und wie lange seine Dialyse stattfinden wird.
        // "${Benutzer!!.get("name")}" holt den Namen des Benutzers bzw. des gesuchten Patienten aus dem Benutzerobjekt und setzt ihn in den Text ein.
        // "${furhat.voice.pause("1000ms")}" fügt eine Pause von 1 Sekunde (1000 Millisekunden) in die Sprachausgabe ein.
        furhatsay(furhat=this.furhat,
            englishText = "Alright, ${Benutzer!!.get("name")}. ${furhat.voice.emphasis("please")} go to " +
                    " the room${furhat.voice.emphasis("$raumy")}  ${furhat.voice.pause("1000ms") } to bed  ${furhat.voice.emphasis("$platzx")} ." +
                    "Your dialysis will start at $dialysebeginn and end at $dialyseende ",
            germanText =  "Gut, ${Benutzer!!.get("name")}. Ich würde Sie ${furhat.voice.emphasis("bittten")} in " +
                    "den Raum${furhat.voice.emphasis("$raumy")}   ${furhat.voice.pause("1000ms") } an den  Platz ${furhat.voice.emphasis("$platzx")} " +
                    "zu gehen. Ihre Dialyse fängt um $dialysebeginn an und endet um $dialyseende ",
            turkishText = "Tamamdır, ${Benutzer!!.get("isim")}. Lütfen oda numara " +
                    "${furhat.voice.emphasis("$raumy")}  ${furhat.voice.pause("1000ms") } 'ya gidin. Yer numaraniz  ${furhat.voice.emphasis("$platzx")}  " +
                    ". Diyaliziniz saat $dialysebeginn 'da başlayacak ve $dialyseende bitecektir",
            romanianText = "Bine ${Benutzer!!.get("name")}., v-aș ruga să mergeți în camera ${furhat.voice.emphasis("$raumy")}  " +
                    "${furhat.voice.pause("1000ms") },la locul ${furhat.voice.emphasis("$platzx")}. Dializa dumneavoastră începe la $dialysebeginn  și se termină la $dialyseende ",
            sprache = Benutzer!!.get("sprache") as Language)

        // Kurze Pause, um dem Benutzer Zeit zum Verarbeiten der Informationen zu geben.
        delay(1000)


        // Furhat nickt und lächelt
  furhat.gesture(Gestures.Nod())
  furhat.gesture(Gestures.BigSmile)

        // Weiterleitung zum nächsten Zustand "NoGUI".
  goto(NoGUI)


}

    onUserLeave {
        // Wenn der Benutzer den Interaktionsbereich verlässt, verabschiedet sich Furhat in der gewählten Sprache.
            furhatsay(furhat=this.furhat,
                englishText = "Thank you as well, it was a pleasure",
                germanText =  "Ich danke ebenfalls, es war mir eine Freude",
                turkishText = "Ben de teşekkür ederim, yardımcı olabildiysem ne mutlu bana",
                romanianText = "Și eu vă mulțumesc, a fost o plăcere",
                sprache = Benutzer!!.get("sprache") as Language)

        delay(1000)
        goto(Idle)
    }

    onReentry {

        // Wenn der Zustand erneut betreten wird, wiederholt Furhat die relevanten Informationen für den Benutzer.
      val raumy: Any = Benutzer!!.get("raum")
      val platzx: Any = Benutzer!!.get("platz")
      val dialysebeginn: Any = furhat.voice.sayAs(Benutzer!!.get("dialysebeginn").toString(), Voice.SayAsType.TIME)


      furhatsay(furhat=this.furhat,
          englishText = " Your dialysis will start at $dialysebeginn start in room $raumy, bed $platzx",
          germanText =  " Ihre Dialyse beginnt um $dialysebeginn im Raum $raumy, am platz $platzx",
          turkishText = "Diyaliziniz oda $raumy'de, yer $platzx'de $dialysebeginn başlangıcında başlayacaktır",
          romanianText = "Dializa dumneavoastră va începe la $dialysebeginn in camera $raumy, la locul $platzx",
          sprache = Benutzer!!.get("sprache") as Language)


      delay(4000)
      goto(NoGUI)
    }
}


