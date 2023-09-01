package furhatos.app.furgui

import furhatos.app.furgui.flow.Parent
import furhatos.app.furgui.flow.helper.ReadExcel
import furhatos.app.furgui.flow.main.Benutzer
import furhatos.app.furgui.flow.main.Idle
import furhatos.event.senses.SenseSkillGUIConnected
import furhatos.flow.kotlin.*
import furhatos.records.Record
import furhatos.skills.HostedGUI

// Our GUI declaration
val GUI = HostedGUI("ExampleGUI", "assets/exampleGui", PORT)
val VARIABLE_SET = "VariableSet"
val CLICK_BUTTON = "ClickButton"

// Starting state, before our GUI has connected.
val NoGUI: State = state(null) {
    onEvent<SenseSkillGUIConnected> {
        goto(GUIConnected)
    }
}

/*
    Here we know our GUI has connected. Since the user might close down the GUI and then reopen
    again, we inherit our handler from the NoGUI state. An edge case might be that a second GUI
    is opened, but this is not accounted for here.

 */
val GUIConnected = state(NoGUI) {
    onEntry {

       // send(DataDelivery(imageName = "dialyse_C_map.png"))
      //  furhat.say("Hallo")
        // Pass data to GUI


        val Nutzerdaten = Benutzer?.get("raum")
        furhat.say("$Nutzerdaten")

      //  val Nutzerdaten: Any? = Benutzer!!.get("raum")


      //  furhat.say("$Nutzerdaten")

      // val Nutzerdaten = "K"

        if (Nutzerdaten == "A"){
            send(DataDelivery(imageName = "dialyse_A_map.png"))
            furhat.say("$Nutzerdaten")
        }

        if (Nutzerdaten == "B"){
            send(DataDelivery(imageName = "dialyse_B _map.png"))
            furhat.say("halllloooo")
        }

        if (Nutzerdaten == "C") {
            send(DataDelivery(imageName = "dialyse_C_map.png"))
            furhat.say("halllloooo")
        }

        if (Nutzerdaten == "D"){
            send(DataDelivery(imageName = "dialyse_D_map.png"))
            furhat.say("halllloooo")
        }

        if (Nutzerdaten == "E"){
            send(DataDelivery(imageName = "dialyse_E_map.png"))
            furhat.say("halllloooo")
        }

        if (Nutzerdaten == "F"){
            send(DataDelivery(imageName = "dialyse_F_map.png"))
            furhat.say("halllloooo")
        }

        if (Nutzerdaten == "G"){
            send(DataDelivery(imageName = "dialyse_G_map.png"))
            furhat.say("halllloooo")
        }


        if (Nutzerdaten == "i"){
            send(DataDelivery(imageName = "dialyse_H_map.png"))
            furhat.say("halllloooo")
        }

        if (Nutzerdaten == "J"){
            send(DataDelivery(imageName = "dialyse_H_map.png"))
            furhat.say("halllloooo")
        }

       if (Nutzerdaten == "K"){
            send(DataDelivery(imageName = "dialyse_K_map.png"))
            furhat.say("Bingo")
        }

        if (Nutzerdaten == "L"){
            send(DataDelivery(imageName = "dialyse_K_map.png"))
            furhat.say("halllloooo")
        }

        if (Nutzerdaten == "M"){
            send(DataDelivery(imageName = "dialyse_K_map.png"))
            furhat.say("halllloooo")
        }

        if (Nutzerdaten == "N"){
            send(DataDelivery(imageName = "dialyse_K_map.png"))
            furhat.say("halllloooo")
        }

        if (Nutzerdaten == "Notfall"){
            send(DataDelivery(imageName = "dialyse_K_map.png"))
            furhat.say("halllloooo")
        }
        delay(9000)

        goto(Idle)
    }


}