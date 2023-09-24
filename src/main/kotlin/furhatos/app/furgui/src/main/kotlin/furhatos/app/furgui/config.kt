package furhatos.app.furgui

import furhatos.event.Event

// Definiert den Port, auf dem die GUI erreichbar ist.
val PORT = 1234


// Ein benutzerdefiniertes Event, das dazu dient, Daten an die GUI zu übermitteln.
class DataDelivery(
        val imageName : String
) : Event()


