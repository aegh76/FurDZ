import furhatos.nlu.Intent
//Erklärung: Vergleiche Klasse "nlu.Ja"

class WelcherPlatz : Intent() {
    //var name: String = ""
    //var vorname: String = ""
    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf(
            "Welcher Platz",
            "Welcher Raum",
            "Welcher Raum nochmal",
            "Welcher Platz nochmal",
            "wohin muss ich nochmal",
            "Wohin", "Nochmal bitte",
            "Raum",
            "Welcher Platz",
            "Was hat er gesagt",
            "Welchen Raum",
            "Welchen Platz",
            "Wohin",
            "Wohin muss ich",
            "Wohin muss ich nochmal",
            "Was meintest du",
            "Wohin",
            "Wo nochmal",
            "Wohin nochmal",
            "Welcher nochmal",
            "Welches Zimmer",
            "welcher Baum",

            "What space.",
            "Which room",
            "Which room again",
            "what room again",
            "where to go again",
            "Where to again",
            "bed", "which bed", "what bed",
            "Where should i go",
            "What did you say",
            "what room",
            "Where to",
            "Where do I have to go",
            "Where do I have to go again",
            "What did you mean",
            "Where again",
            "Which one again",
            "what", "where",

            "Hangi alan.",
            "Hangi oda",
            "Yine hangi oda",
            "Yine hangi oda",
            "tekrar nereye gideceğim",
            "Yine nereye",
            "yatak", "hangi yatak", "hangi yatak",
            "Nereye gitmeliyim",
            "Ne dedin sen?",
            "Hangi oda",
            "Nereye",
            "Nereye gitmem gerekiyor?",
            "Yine nereye gitmem gerekiyor?",
            "Ne demek istedin?",
            "Yine nerede",
            "Hangisi demiştin?",
            "Ne", "Nerede",

            "Ce spațiu.",
            "Care cameră",
            "Care cameră din nou",
            "Ce cameră din nou",
            "unde să mergem din nou",
            "Încotro din nou",
            "locul", "care loc", "ce loc", "care loc", "la ce loc trebuie sa ma duc?",
            "Unde ar trebui să mă duc",
            "Ce ai spus",
            "ce cameră",
            "Încotro",
            "Unde trebuie să mă duc",
            "Unde trebuie să mă duc din nou",
            "Ce ai vrut să spui",
            "Unde din nou",
            "Care din nou",
            "ce", "unde", "are camera"
        )
    }
    /*fun getFullName(): String =
        "$vorname $name"
*/
}