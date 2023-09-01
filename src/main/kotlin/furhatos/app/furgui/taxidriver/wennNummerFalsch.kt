package furhatos.app.furgui.taxidriver


import furhatos.app.furgui.flow.Parent
import furhatos.app.furgui.flow.helper.GetDigitsKundenochmal
import furhatos.app.furgui.flow.main.Benutzer
import furhatos.flow.kotlin.*

val WennNummerFalschPatient : State = state(Parent) {
    onEntry {
        //Bevor das field Patientennummer neu überschrieben wird, wird es genullt.
        Benutzer!!.put("Patientennummer", null)
        GetDigitsKundenochmal(Benutzer!!, this.furhat)
        goto(ValidierungNummerKunde)
    }
}

