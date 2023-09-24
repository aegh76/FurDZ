package furhatos.app.furgui.flow



import furhatos.app.furgui.flow.main.Idle
import furhatos.app.furgui.setting.DISTANCE_TO_ENGAGE
import furhatos.app.furgui.setting.MAX_NUMBER_OF_USERS
import furhatos.autobehavior.enableSmileBack
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.flow.kotlin.*


// Der "Init" State initialisiert verschiedene Parameter und Einstellungen, bevor der Skill startet.
val Init : State = state {
    init {

        // Startet die Protokollierung der Dialoge mit Furhat für spätere Analysen oder Überprüfungen.
        dialogLogger.startSession()


        // Setzt die Standard-Mikroexpressionen für Furhat, um eine realistischere Interaktion zu ermöglichen.
        furhat.setDefaultMicroexpression(blinking = true, facialMovements = false, eyeMovements = true)

        // Aktiviert die Funktion, dass Furhat zurücklächelt, wenn er ein Lächeln erkennt.
        furhat.enableSmileBack = true

        // Legt die Richtlinien für die Benutzerinteraktion mit Furhat fest, z.B. wie viele Benutzer gleichzeitig interagieren können.
        users.setSimpleEngagementPolicy(DISTANCE_TO_ENGAGE, MAX_NUMBER_OF_USERS)

        // Aktiviert die Kamera von Furhat, um die Benutzerinteraktion zu ermöglichen.
        furhat.cameraFeed.enable()

        //Mit dem goto() Befehl definiert man welcher State der Anwendung als nächstes eingeleitet wird.
        goto(Idle)

    }
}