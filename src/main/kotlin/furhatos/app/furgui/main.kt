package furhatos.app.furgui

import furhatos.app.furgui.flow.Init
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class FurguiSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
