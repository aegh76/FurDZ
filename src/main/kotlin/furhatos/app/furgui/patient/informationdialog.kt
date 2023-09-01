package furhatos.app.furgui.patient

import Danke
import FrageWiederholen
import Nein
import WelcherPlatz
import furhatos.app.furgui.NoGUI
import nlu.Ja
import furhatos.app.furgui.flow.Parent
import furhatos.app.furgui.flow.helper.furhatsay
import furhatos.app.furgui.flow.main.Benutzer
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice
import furhatos.gestures.Gestures
import furhatos.skills.UserManager.current
import furhatos.util.Language


val Informationsdialogue : State = state(Parent) {

    onEntry {


        //Zunächst werden die Werte raumy, platzx, dialysebeginn und dialyseende mit den fields raum, platz,
        //dialysebeginn und dialyseende des jeweiligen Patienten gesetzt und entsprechend manipuliert.
        val raumy: Any? = Benutzer!!.get("raum")
        val platzx: Any? = Benutzer!!.get("platz")
        val dialysebeginn: Any? = furhat.voice.sayAs(Benutzer!!.get("dialysebeginn").toString(), Voice.SayAsType.TIME)
        val dialyseende: Any? = furhat.voice.sayAs(Benutzer!!.get("dialyseende").toString(), Voice.SayAsType.TIME)
        // val datum = furhat.voice.sayAs(Benutzer!!.get("datum").toString(), Voice.SayAsType.TIME)


        /*  val currentDateTime = LocalDateTime.now(ZoneId.of("Europe/Berlin"))
          val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
          val formattedDateTime = currentDateTime.format(formatter)*/




        //Der Nutzer wird über seine Termindaten informiert und weiß somit, wann, wo und wie lange seine Dialyse
        //stattfinden wird.

        furhatsay(furhat=this.furhat,
            englishText = "Alright, ${Benutzer!!.get("name")}. ${furhat.voice.emphasis("please")} go to " +
                    " the room${furhat.voice.emphasis("$raumy")}  ${furhat.voice.pause("1000ms") } to bed  ${furhat.voice.emphasis("$platzx")} ." +
                    "Your dialysis will start at $dialysebeginn start and end at $dialyseende ",
            germanText =  "Gut, ${Benutzer!!.get("name")}. Ich würde Sie ${furhat.voice.emphasis("bittten")} in " +
                    "den Raum${furhat.voice.emphasis("$raumy")}   ${furhat.voice.pause("1000ms") } an den  Platz ${furhat.voice.emphasis("$platzx")} ." +
                    "zu gehen. Ihre Dialyse fängt um $dialysebeginn an und endet um $dialyseende ",
            turkishText = "Tamamdır, ${Benutzer!!.get("isim")}. Lütfen oda numara " +
                    "${furhat.voice.emphasis("$raumy")}  ${furhat.voice.pause("1000ms") } 'ya gidin. Yer numaraniz  ${furhat.voice.emphasis("$platzx")} . " +
                    ". Diyaliziniz saat $dialysebeginn 'da başlayacak ve $dialyseende bitecektir",
            romanianText = "Bine ${Benutzer!!.get("name")}., v-aș ruga să mergeți în camera ${furhat.voice.emphasis("$raumy")}  " +
                    "${furhat.voice.pause("1000ms") },la locul ${furhat.voice.emphasis("$platzx")}. Dializa dumneavoastră începe la $dialysebeginn  și se termină la $dialyseende ",
            sprache = Benutzer!!.get("sprache") as Language)



        delay(1000)

  /* furhatsay(furhat=this.furhat,
      englishText = "the current time and date is: $formattedDateTime",
      germanText =  "Aktuelle Uhrzeit und Datum ist: $formattedDateTime",
      turkishText = "Geçerli saat ve tarih: $formattedDateTime",
      romanianText = "Data și ora curentă: $formattedDateTime",
      sprache = Benutzer!!.get("sprache") as Language)

  delay(1000) */

        furhatsay(furhat=this.furhat,
                  englishText = "I hope I could help you have a ${furhat.voice.emphasis("beautiful")} day",
                  germanText =  "Ich hoffe ich konnte Ihnen helfen, einen ${furhat.voice.emphasis("schönen")} Tag noch",
                  turkishText = "umarım yardımcı olabilmişimdir. Iyi günler dilerim!",
                  romanianText = "Sper că v-aș putea ajuta să aveți inca o zi ${furhat.voice.emphasis("frumoasă")} ",
                  sprache = Benutzer!!.get("sprache") as Language)

  furhat.gesture(Gestures.Nod())
  furhat.gesture(Gestures.BigSmile)



  //Für 10 Sekunden hört Furhat dann seinem Gesprächspartner zu, falls noch Fragen bezüglich der Platzinformation
  //offen sind, kann furhat die Informationen nochmal wiederholen. Der State wird dann nicht nochmal von vorne
  //begonnen, sondern startet bei onReentry (Zeile 65).
  furhat.listen(timeout = 6000)

  goto(NoGUI)


}
onResponse<FrageWiederholen> {
  furhat.attend(user= current)
  reentry()
}

onResponse<WelcherPlatz> {
  furhat.attend(user= current)
  goto(NoGUI)
}
onNoResponse {
  delay(6000)
  goto(NoGUI)
}
onResponse<Danke> {
  furhatsay(furhat=this.furhat,
      englishText = "Thank you as well, it was a pleasure",
      germanText =  "Ich danke ebenfalls, es war mir eine Freude",
      turkishText = "Ben de teşekkür ederim, yardımcı olabildiysem ne mutlu bana",
      romanianText = "Și eu vă mulțumesc, a fost o plăcere",
      sprache = Benutzer!!.get("sprache") as Language)

  delay(4000)
  goto(NoGUI)
}
onResponse<Ja> {
  goto(NoGUI)
}
onResponse<Nein> {
  goto(NoGUI)
}
onReentry {
  //Falls diese wiederholt werden müssen, teilt Furhat dem Gesprächspartner noch einmal die relevanten Daten mit.
  val raumy: Any? = Benutzer!!.get("raum")
  val platzx: Any? = Benutzer!!.get("platz")
  val dialysebeginn: Any? = furhat.voice.sayAs(Benutzer!!.get("dialysebeginn").toString(), Voice.SayAsType.TIME)


  furhatsay(furhat=this.furhat,
      englishText = " Your dialysis will start at $dialysebeginn start in room $raumy, bed $platzx",
      germanText =  " Ihre Dialyse beginnt um $dialysebeginn im Raum $raumy, am platz $platzx",
      turkishText = "Diyaliziniz oda $raumy'de, yer $platzx'de $dialysebeginn başlangıcında başlayacaktır",
      romanianText = "Dializa dumneavoastră va începe la $dialysebeginn in camera $raumy, la locul $platzx",
      sprache = Benutzer!!.get("sprache") as Language)


  delay(3000)
  goto(NoGUI)
}
}


