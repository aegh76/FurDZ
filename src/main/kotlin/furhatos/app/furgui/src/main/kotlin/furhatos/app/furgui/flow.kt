package furhatos.app.furgui


import RepeatQuestion
import Thanks
import WhichRoom
import furhatos.app.furgui.flow.Parent
import furhatos.app.furgui.flow.helper.furhatask
import furhatos.app.furgui.flow.helper.furhatsay
import furhatos.app.furgui.flow.main.Benutzer
import furhatos.app.furgui.flow.main.Idle
import furhatos.event.senses.SenseSkillGUIConnected
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice
import furhatos.skills.HostedGUI

import furhatos.util.Language

// Deklaration der GUI mit dem Namen "ExampleGUI" und dem Pfad "assets/exampleGui"
val GUI = HostedGUI("ExampleGUI", "assets/exampleGui", PORT)


// Startzustand, bevor die GUI verbunden ist.
val NoGUI: State = state(Parent) {
    // Wenn die GUI verbunden ist, wechsle zum Zustand "GUIConnected"
    onEvent<SenseSkillGUIConnected> {
        goto(GUIConnected)
    }
}

// Zustand, wenn die GUI verbunden ist.
val GUIConnected = state(NoGUI) {
    onEntry {
        // Abrufen der Benutzerdaten von der CSV Datei
        val Nutzerdaten = Benutzer?.get("raum")

        // Abhängig von den Benutzerdaten wird ein bestimmtes Bild gesendet
        // und Furhat gibt entsprechende Anweisungen aus.
        if (Nutzerdaten == "A"){

            send(DataDelivery(imageName = "dialyse_A_map.png"))

           /* furhatsay(
                furhat = this.furhat,
                englishText = "Go to room A. Walk straight through the sliding door and continue through the double doors. Then turn left and walk a short distance. Room A is on the left.",
                germanText = "Gehen Sie zum Raum A. Laufen Sie geradeaus durch die Schiebetür und weiter durch die Doppeltür. Biegen Sie dann links ab und gehen Sie ein Stück weiter. Raum A befindet sich auf der linken Seite.",
                turkishText = "A odasına gidin. Sürgülü kapıdan dümdüz ilerleyin ve çift kapıdan geçin. Sonra sola dönün ve biraz daha yürüyün. A odası solda.",
                romanianText = "Mergeți în camera A. Mergeți drept înainte prin ușa glisantă și mai departe prin ușa dublă. Apoi întoarceți-vă la stânga și mergeți puțin mai departe. Camera A este pe stânga.",
                sprache = Benutzer!!.get("sprache") as Language
            )

            */

        }



        if (Nutzerdaten == "B"){

            send(DataDelivery(imageName = "dialyse_B _map.png"))

/*   furhatsay(
      furhat = this.furhat,
      englishText = "Go to room B. Walk straight through the sliding door and continue through the double doors. Then turn left and walk a little further along the corridor. Room B is on the left, just after room A.",
      germanText = "Gehen Sie zum Raum B. Laufen Sie geradeaus durch die Schiebetür und weiter durch die Doppeltür. Biegen Sie dann links ab und gehen Sie ein Stück weiter den Gang entlang. Raum B befindet sich auf der linken Seite, kurz nach Raum A.",
      turkishText = "B odasına gidin. Sürgülü kapıdan dümdüz ilerleyin ve çift kapıdan geçin. Sonra sola dönün ve koridor boyunca biraz daha yürüyün. B odası solda, A odasından hemen sonra.",
      romanianText = "Mergeți în camera B. Mergeți drept înainte prin ușa glisantă și mai departe prin ușa dublă. Apoi întoarceți-vă la stânga și mergeți puțin mai departe pe coridor. Camera B este pe stânga, imediat după camera A.",
      sprache = Benutzer!!.get("sprache") as Language
  )

 */

}

        if (Nutzerdaten == "C") {

             send(DataDelivery(imageName = "dialyse_C_map.png"))

    /* furhatsay(
    furhat = this.furhat,
    englishText = "Go to room C. Walk straight through the sliding door and continue through the double doors. Then turn left and follow the hallway to the end. Room C is then directly in front of you.",
    germanText = "Gehen Sie zum Raum C. Laufen Sie geradeaus durch die Schiebetür und weiter durch die Doppeltür. Biegen Sie dann links ab und folgen Sie dem Gang bis zum Ende. Zimmer C befindet sich dann direkt vor Ihnen.",
    turkishText = "C odasına gidin. Sürgülü kapıdan dümdüz ilerleyin ve çift kapıdan geçin. Sonra sola dönün ve koridoru sonuna kadar takip edin. C odası tam karşınızda.",
    romanianText = "Mergeți în camera C. Mergeți drept înainte prin ușa glisantă și mai departe prin ușa dublă. Apoi faceți la stânga și urmați coridorul până la capăt. Camera C se află apoi direct în fața ta.",
    sprache = Benutzer!!.get("sprache") as Language
    )

     */

}

        if (Nutzerdaten == "D"){

        send(DataDelivery(imageName = "dialyse_D_map.png"))

/*furhatsay(
   furhat = this.furhat,
   englishText = "Go to room D. Walk straight through the sliding door and continue through the double doors. Then turn left and follow the hallway to the end. Go a short distance to the right and Room D will be on your left.",
   germanText = "Gehen Sie zum Raum D. Laufen Sie geradeaus durch die Schiebetür und weiter durch die Doppeltür. Biegen Sie dann links ab und folgen Sie dem Gang bis zum Ende. Gehen Sie ein kleines Stück nach rechts, und Raum D befindet sich dann auf Ihrer linken Seite.",
   turkishText = "D odasına gidin. Sürgülü kapıdan dümdüz ilerleyin ve çift kapıdan geçin. Sonra sola dönün ve koridoru sonuna kadar takip edin. Biraz sağa gidin ve D odası solunuzda kalacak.",
   romanianText = "Mergeți în camera D. Mergeți drept înainte prin ușa glisantă și mai departe prin ușa dublă. Apoi întoarceți-vă la stânga și urmați coridorul până la capăt. Mergeți puțin la dreapta și camera D va fi pe stânga.",
   sprache = Benutzer!!.get("sprache") as Language
)

 */

}

        if (Nutzerdaten == "E"){

        send(DataDelivery(imageName = "dialyse_E_map.png"))

        /*furhatsay(
          furhat = this.furhat,
          englishText = "Go to room E. Walk straight through the sliding door and continue through the double doors. Then turn left and follow the corridor to the end. Go a little to the right and walk down the hallway a little further. Room E will then be on your left.",
          germanText = "Gehen Sie zum Raum E. Laufen Sie geradeaus durch die Schiebetür und weiter durch die Doppeltür. Biegen Sie dann links ab und folgen Sie dem Gang bis zum Ende. Gehen Sie ein Stück nach rechts und laufen Sie den Gang etwas weiter hinunter. Raum E befindet sich dann auf Ihrer linken Seite.",
          turkishText = "E odasına gidin. Sürgülü kapıdan dümdüz ilerleyin ve çift kapıdan geçin. Sonra sola dönün ve koridoru sonuna kadar takip edin. Biraz sağa gidin ve koridorda biraz daha yürüyün. E odası solunuzda kalacak.",
          romanianText = "Mergeți în camera E. Mergeți drept înainte prin ușa glisantă și mai departe prin ușa dublă. Apoi faceți la stânga și urmați coridorul până la capăt. Mergeți puțin la dreapta și mergeți pe coridor puțin mai departe. Camera E va fi apoi pe stânga.",
          sprache = Benutzer!!.get("sprache") as Language
        )

         */

        }

        if (Nutzerdaten == "F"){

        send(DataDelivery(imageName = "dialyse_F_map.png"))

        /*furhatsay(
         furhat = this.furhat,
         englishText = "Go to room F. Walk straight ahead to the end of the corridor and pass the second double door. Then turn left and walk until just before the second double door in the hallway. Room F will then be on your right.",
         germanText = "Gehen Sie zum Raum F. Laufen Sie geradeaus bis zum Ende des Ganges und passieren Sie die zweite Doppeltür. Biegen Sie dann links ab und gehen Sie bis kurz vor die zweite Doppeltür im Gang. Raum F befindet sich dann auf Ihrer rechten Seite.",
         turkishText = "F odasına gidin. Koridorun sonuna kadar dümdüz yürüyün ve ikinci çift kapıyı geçin. Sonra sola dönün ve koridordaki ikinci çift kapının hemen önüne kadar yürüyün. F odası sağınızda kalacak.",
         romanianText = "Mergeți în camera F. Mergeți drept înainte până la capătul coridorului și treceți de a doua ușă dublă. Apoi întoarceți-vă la stânga și mergeți până chiar înainte de a doua ușă dublă de pe coridor. Camera F va fi apoi la dreapta ta.",
         sprache = Benutzer!!.get("sprache") as Language
        )

         */

        }

        if (Nutzerdaten == "G"){

        send(DataDelivery(imageName = "dialyse_G_map.png"))

        /*furhatsay(
        furhat = this.furhat,
        englishText = "Go to room G. Walk straight to the end of the corridor and pass the second double door. Then turn left. Shortly after the direction of the corridor changes, Room G will be on your right.",
        germanText = "Gehen Sie zum Raum G. Laufen Sie geradeaus bis zum Ende des Ganges und passieren Sie die zweite Doppeltür. Biegen Sie dann links ab. Kurz nachdem sich die Richtung des Ganges ändert, befindet sich Raum G auf Ihrer rechten Seite.",
        turkishText = "G odasına gidin. Koridorun sonuna kadar dümdüz yürüyün ve ikinci çift kapıdan geçin. Sonra sola dönün. Koridorun yönü değiştikten kısa bir süre sonra G odası sağınızda kalacak.",
        romanianText = "Du-te în camera G. Mergeți drept înainte până la capătul coridorului și treceți prin a doua ușă dublă. Apoi întoarceți-vă la stânga. La scurt timp după ce direcția coridorului se schimbă, camera G se află pe partea dreaptă.",
        sprache = Benutzer!!.get("sprache") as Language
        )

         */

        }


        if (Nutzerdaten == "i"){

        send(DataDelivery(imageName = "dialyse_i_map.png"))

        /*furhatsay(
        furhat = this.furhat,
        englishText = "Go to room i. Walk straight through the sliding door and continue through the double door. Then turn left and follow the corridor to the end. Then go right and run down the corridor. After the next double door, room i will be on your left.",
        germanText = "Gehen Sie zum Raum i. Laufen Sie geradeaus durch die Schiebetür und weiter durch die Doppeltür. Biegen Sie dann links ab und folgen Sie dem Gang bis zum Ende. Gehen Sie anschließend rechts und laufen Sie den Gang hinunter. Nach der nächsten Doppeltür befindet sich Raum i auf Ihrer linken Seite.",
        turkishText = "Oda i'ye gidin. Sürgülü kapıdan dümdüz ilerleyin ve çift kapıdan devam edin. Sonra sola dönün ve koridoru sonuna kadar takip edin. Sonra sağa dön ve koridordan aşağı koş. Bir sonraki çift kapıdan sonra, oda i solunuzda.",
        romanianText = "Mergeți în camera i. Mergeți drept înainte prin ușa glisantă și continuați prin ușa dublă. Apoi întoarceți-vă la stânga și urmați coridorul până la capăt. Apoi mergeți la dreapta și alergați pe coridor. După următoarea ușă dublă, camera i se află pe partea stângă.",
        sprache = Benutzer!!.get("sprache") as Language
        )

         */

        }

        if (Nutzerdaten == "J"){

        send(DataDelivery(imageName = "dialyse_J_map.png"))

        /*furhatsay(
        furhat = this.furhat,
          englishText = "Go to room J. Walk straight through the sliding door and continue through the double doors. Then turn left and follow the corridor to the end. Then turn right and walk along the corridor until you reach the next double door. Room J is located as the second door on the left after the double door.",
          germanText = "Gehen Sie zum Raum J. Laufen Sie geradeaus durch die Schiebetür und weiter durch die Doppeltür. Biegen Sie dann links ab und folgen Sie dem Gang bis zum Ende. Anschließend biegen Sie rechts ab und gehen den Gang entlang bis zur nächsten Doppeltür. Der Raum J befindet sich als zweite Tür auf der linken Seite nach der Doppeltür.",
          turkishText = "J odasına gidin. Sürgülü kapıdan dümdüz ilerleyin ve çift kapıdan geçin. Sonra sola dönün ve koridoru sonuna kadar takip edin. Sonra sağa dönün ve bir sonraki çift kapıya ulaşana kadar koridor boyunca yürüyün. J odası çift kapıdan sonra soldaki ikinci kapıdır.",
          romanianText = "Mergeți în camera J. Mergeți drept înainte prin ușa glisantă și mai departe prin ușa dublă. Apoi faceți la stânga și urmați coridorul până la capăt. Apoi întoarceți-vă la dreapta și mergeți de-a lungul coridorului până când ajungeți la următoarea ușă dublă. Camera J este a doua ușă pe stânga după ușa dublă.",
          sprache = Benutzer!!.get("sprache") as Language
        )

         */

        }

        if (Nutzerdaten == "K"){

        send(DataDelivery(imageName = "dialyse_K_map.png"))

        /*furhatsay(
        furhat = this.furhat,
        englishText = "Go to room K. Walk straight through the sliding door and continue through the double door. Then turn left and follow the corridor to the end. Then go right and run down the corridor. Go through the next double door and the last room on the left is room K.",
        germanText = "Gehen Sie zum Raum K. Laufen Sie geradeaus durch die Schiebetür und weiter durch die Doppeltür. Biegen Sie dann links ab und folgen Sie dem Gang bis zum Ende. Gehen Sie anschließend rechts und laufen Sie den Gang hinunter. Durchqueren Sie die nächste Doppeltür und der letzte Raum auf der linken Seite ist Raum K.",
        turkishText = "K odasına gidin. Sürgülü kapıdan dümdüz ilerleyin ve çift kapıdan geçin. Sonra sola dönün ve koridoru sonuna kadar takip edin. Sonra sağa dönün ve koridordan aşağı koşun. Bir sonraki çift kapıdan geçin ve soldaki son oda K odasıdır.",
        romanianText = "Mergeți în camera K. Mergeți drept înainte, treceți prin ușa glisantă și apoi prin ușa dublă. Apoi faceți la stânga și urmați coridorul până la capăt. Apoi mergeți la dreapta și alergați pe coridor. Treceți prin următoarea ușă dublă și ultima cameră de pe stânga este camera K.",
        sprache = Benutzer!!.get("sprache") as Language
        )

         */

        }

        if (Nutzerdaten == "L"){
        send(DataDelivery(imageName = "dialyse_L_map.png"))

        /*furhatsay(
        furhat = this.furhat,
        englishText = "Go to room L. Walk straight through the sliding door and continue through the double door. Then turn left and follow the corridor to the end. Then go right and run down the corridor. Go through the next double door and the last room on the right is room L.",
        germanText = "Gehen Sie zum Raum L. Laufen Sie geradeaus durch die Schiebetür und weiter durch die Doppeltür. Biegen Sie dann links ab und folgen Sie dem Gang bis zum Ende. Gehen Sie anschließend rechts und laufen Sie den Gang hinunter. Durchqueren Sie die nächste Doppeltür und der letzte Raum auf der rechten Seite ist Raum L.",
        turkishText = "L odasına gidin. Sürgülü kapıdan dümdüz ilerleyin ve çift kapıdan geçin. Sonra sola dönün ve koridoru sonuna kadar takip edin. Sonra sağa dönün ve koridordan aşağı koşun. Bir sonraki çift kapıdan geçin ve sağdaki son oda L odasıdır.",
        romanianText = "Mergeți în camera L. Mergeți drept înainte prin ușa glisantă și mai departe prin ușa dublă. Apoi faceți la stânga și urmați coridorul până la capăt. Apoi mergeți la dreapta și alergați pe coridor. Treceți prin următoarea ușă dublă și ultima cameră pe dreapta este camera L.",
        sprache = Benutzer!!.get("sprache") as Language
        )

         */

        }

        if (Nutzerdaten == "M"){

        send(DataDelivery(imageName = "dialyse_M_map.png"))

        /*furhatsay(
        furhat = this.furhat,
        englishText = "Go to room M. Walk straight through the sliding door and continue through the double door. Then turn left and follow the corridor to the end. Then turn right and walk along the corridor until you reach the next double door. Room M is located as the second door on the right side after the double door.",
        germanText = "Gehen Sie zum Raum M. Laufen Sie geradeaus durch die Schiebetür und weiter durch die Doppeltür. Biegen Sie dann links ab und folgen Sie dem Gang bis zum Ende. Anschließend biegen Sie rechts ab und gehen den Gang entlang bis zur nächsten Doppeltür. Der Raum M befindet sich als zweite Tür auf der rechten Seite nach der Doppeltür.",
        turkishText = "M odasına gidin. Sürgülü kapıdan dümdüz ilerleyin ve çift kapıdan geçin. Sonra sola dönün ve koridoru sonuna kadar takip edin. Sonra sağa dönün ve bir sonraki çift kapıya ulaşana kadar koridor boyunca yürüyün. M odası çift kapıdan sonra sağdaki ikinci kapıdır.",
        romanianText = "Mergeți în camera M. Mergeți drept înainte prin ușa glisantă și mai departe prin ușa dublă. Apoi faceți la stânga și urmați coridorul până la capăt. Apoi virați la dreapta și mergeți de-a lungul coridorului până când ajungeți la următoarea ușă dublă. Camera M este a doua ușă la dreapta după ușa dublă.",
        sprache = Benutzer!!.get("sprache") as Language
        )

         */

        }

        if (Nutzerdaten == "N"){

        send(DataDelivery(imageName = "dialyse_N_map.png"))

        /*furhatsay(
        furhat = this.furhat,
        englishText = "Go to room N. Walk straight through the sliding door and continue through the double door. Then turn left and follow the corridor to the end. Then go right and run down the corridor. After the next double door, Room N will be on your right.",
        germanText = "Gehen Sie zum Raum N. Laufen Sie geradeaus durch die Schiebetür und weiter durch die Doppeltür. Biegen Sie dann links ab und folgen Sie dem Gang bis zum Ende. Gehen Sie anschließend rechts und laufen Sie den Gang hinunter. Nach der nächsten Doppeltür befindet sich Raum N auf Ihrer rechten Seite.",
        turkishText = "N odasına gidin. Sürgülü kapıdan dümdüz ilerleyin ve çift kapıdan geçin. Sonra sola dönün ve koridoru sonuna kadar takip edin. Sonra sağa dönün ve koridordan aşağı koşun. Bir sonraki çift kapıdan sonra N odası sağınızda.",
        romanianText = "Mergeți în camera N. Mergeți drept înainte prin ușa glisantă și mai departe prin ușa dublă. Apoi faceți la stânga și urmați coridorul până la capăt. Apoi mergeți la dreapta și alergați pe coridor. După următoarea ușă dublă, camera N este pe dreapta.",
        sprache = Benutzer!!.get("sprache") as Language
        )

         */
        }

        if (Nutzerdaten == "Notfall"){

        send(DataDelivery(imageName = "dialyse_Notfall_map.png"))

        /*furhatsay(
        furhat = this.furhat,
        englishText = "Go to the Notfall room. Walk straight through the sliding door and continue through the double doors. Then turn left and walk a short distance. Room Notfall is on the right.",
        germanText = "Gehen Sie zum Raum Notfall. Laufen Sie geradeaus durch die Schiebetür und weiter durch die Doppeltür. Biegen Sie dann links ab und gehen Sie ein Stück weiter. Raum Notfall befindet sich auf der rechten Seite.",
        turkishText = "Notfall odaya gidin. Sürgülü kapıdan düz yürüyün ve çift kapıdan devam edin. Sonra sola dönün ve biraz daha yürüyün. Oda Notfall sağ tarafta.",
        romanianText = "Mergeți la camera Notfall. Mergeți drept prin ușa glisantă și continuați prin ușa dublă. Apoi întoarceți-vă la stânga și mergeți puțin mai departe. Camera Notfall se află pe partea dreaptă.",
        sprache = Benutzer!!.get("sprache") as Language
        )*/

        }

        // Abschließende Nachricht von Furhat
        furhatask(furhat=this.furhat,
            englishText = "I hope I could help you have a ${furhat.voice.emphasis("beautiful")} day",
            germanText =  "Ich hoffe ich konnte Ihnen helfen, einen ${furhat.voice.emphasis("schönen")} Tag noch",
            turkishText = "umarım yardımcı olabilmişimdir. Iyi günler dilerim!",
            romanianText = "Sper că v-aș putea ajuta să aveți inca o zi ${furhat.voice.emphasis("frumoasă")} ",
            sprache = Benutzer!!.get("sprache") as Language)

        // Nach einer Verzögerung von 40 Sekunden wechselt Furhat in den Zustand "Idle"
    delay(40000)

    goto(Idle)
    }

    // Wenn der Benutzer nach einer Wiederholung fragt, werden die Anweisungen wiederholt
    onResponse<RepeatQuestion> {
        val raumy: Any? = Benutzer!!.get("raum")
        val platzx: Any? = Benutzer!!.get("platz")
        val dialysebeginn: Any? = furhat.voice.sayAs(Benutzer!!.get("dialysebeginn").toString(), Voice.SayAsType.TIME)


        furhatsay(furhat=this.furhat,
            englishText = " Your dialysis will start at $dialysebeginn start in room $raumy, bed $platzx",
            germanText =  " Ihre Dialyse beginnt um $dialysebeginn im Raum $raumy, am platz $platzx",
            turkishText = "Diyaliziniz oda $raumy'de, yer $platzx'de $dialysebeginn başlangıcında başlayacaktır",
            romanianText = "Dializa dumneavoastră va începe la $dialysebeginn in camera $raumy, la locul $platzx",
            sprache = Benutzer!!.get("sprache") as Language)

    }

    // Wenn der Benutzer nach dem Raum oder Bett fragt, werden die Anweisungen wiederholt
    onResponse<WhichRoom> {
        val raumy: Any? = Benutzer!!.get("raum")
        val platzx: Any? = Benutzer!!.get("platz")
        val dialysebeginn: Any? = furhat.voice.sayAs(Benutzer!!.get("dialysebeginn").toString(), Voice.SayAsType.TIME)


        furhatsay(furhat=this.furhat,
            englishText = " Your dialysis will start at $dialysebeginn start in room $raumy, bed $platzx",
            germanText =  " Ihre Dialyse beginnt um $dialysebeginn im Raum $raumy, am platz $platzx",
            turkishText = "Diyaliziniz oda $raumy'de, yer $platzx'de $dialysebeginn başlangıcında başlayacaktır",
            romanianText = "Dializa dumneavoastră va începe la $dialysebeginn in camera $raumy, la locul $platzx",
            sprache = Benutzer!!.get("sprache") as Language)

    }

    //Furhat geht nach 5 sek in den Zustand "Idle"
    onNoResponse {
    delay(5000)
    goto(Idle)
    }

    //Wenn man sich bedankt
    onResponse<Thanks> {
    furhatsay(furhat=this.furhat,
    englishText = "Thank you as well, it was a pleasure",
    germanText =  "Ich danke ebenfalls, es war mir eine Freude",
    turkishText = "Ben de teşekkür ederim, yardımcı olabildiysem ne mutlu bana",
    romanianText = "Și eu vă mulțumesc, a fost o plăcere",
    sprache = Benutzer!!.get("sprache") as Language)

    delay(4000)
    goto(Idle)
    }

    // Bei erneutem Betreten dieses Zustands werden die Anweisungen erneut ausgegeben
    onReentry {
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
    goto(Idle)
    }
}
