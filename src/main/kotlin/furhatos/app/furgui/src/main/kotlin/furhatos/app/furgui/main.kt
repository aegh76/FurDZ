package furhatos.app.furgui

import furhatos.app.furgui.flow.Init
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

// Definition der Klasse "FurguiSkill", die von der Klasse "Skill" erbt.
class FurguiSkill : Skill() {
    // Überschreiben der Methode "start" aus der übergeordneten Klasse "Skill".
    override fun start() {
        // Starten des Flows mit dem Anfangszustand "Init".
        Flow().run(Init)
    }
}

// Hauptfunktion, die beim Start des Programms aufgerufen wird.
fun main(args: Array<String>) {
    // Aufruf der Hauptfunktion der Klasse "Skill" mit den übergebenen Argumenten.
    Skill.main(args)
}
