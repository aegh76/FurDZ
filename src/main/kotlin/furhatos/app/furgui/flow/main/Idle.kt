package furhatos.app.furgui.flow.main


import furhat.libraries.standard.UsersLib.usersLib
import furhatos.app.furgui.DataDelivery
import furhatos.app.furgui.flow.Parent
import furhatos.flow.kotlin.*
import furhatos.util.Gender
import furhatos.util.Language




val Idle: State = state(Parent) {
    init {


        send(DataDelivery(imageName = "50JahrePHV.jpg"))

        furhat.usersLib.attendClosestUser()

        if(users.count > 0)
        {
            //Befinden sich mehr als 0 User in Furhat's Reichweite, so leuchtet der LED Ring Furhat's in Magenta.
            furhat.ledStrip.solid(java.awt.Color.green)

            //Furhat schaut den ihm nächsten User an
            furhat.usersLib.attendClosestUser()

            //und geht in den nächsten State "(Greetingname") über
            goto(Greetinglanguage)
        }
        //Falls genau 0 User in Furhat's Reichweite sind, schaut er niemanden an.
        else(furhat.attendNobody())
    }

    onEntry {

        furhat.setInputLanguage(Language.ENGLISH_US, Language.GERMAN, Language.TURKISH)
        //Mit furhat.voice wird die NLU ausgewählt, die Furhat für die Interaktion verwenden soll.
        //furhat.voice = PollyVoice.Hans()
        furhat.setVoice(Language.GERMAN, Gender.MALE, false)


        if (users.count < 1) {
            furhat.attendNobody()
        } else {
            furhat.attend(users.other)
            goto(Greetinglanguage)
        }
    }

    onUserEnter {
        furhat.attend(it)
        goto(Greetinglanguage)
    }

    onReentry {
        furhat.attend(users.other)
        goto(Greetinglanguage)
    }

}

