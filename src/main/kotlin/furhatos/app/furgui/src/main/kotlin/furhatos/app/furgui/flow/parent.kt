package furhatos.app.furgui.flow


import furhatos.app.furgui.flow.main.Idle
import furhatos.flow.kotlin.*


// Der "Parent" Abschnitt definiert allgemeine Verhaltensweisen,
// die in anderen Abschnitten des Gesprächs mit Furhat wiederverwendet werden können.
val Parent: State = state {

    // Wenn ein Benutzer in die Nähe von Furhat kommt
    onUserEnter(instant = true) {

        when {
            // Wenn Furhat bereits einen Benutzer anschaut, wirft er nur einen kurzen Blick auf den neuen Benutzer
            furhat.isAttendingUser -> furhat.glance(it)
            // Wenn Furhat keinen Benutzer anschaut, wendet er sich dem neuen Benutzer zu
            !furhat.isAttendingUser -> furhat.attend(it)
        }
    }

    // Wenn ein Benutzer den Bereich von Furhat verlässt
    onUserLeave(instant = true) {
        when {
            // Wenn der letzte Benutzer geht, wendet Furhat sich von allen Benutzern ab und geht in den "Idle" Zustand über
                !users.hasAny() -> {
                furhat.attendNobody()
                goto(Idle)
            }
            // Wenn der Benutzer, den Furhat anschaut, geht, wendet Furhat sich einem anderen Benutzer zu
            furhat.isAttending(it) -> furhat.attend(users.other)
            // Wenn ein anderer Benutzer geht, wirft Furhat nur einen kurzen Blick in dessen Richtung
            !furhat.isAttending(it) -> furhat.glance(it.head.location)
        }

    }

}
