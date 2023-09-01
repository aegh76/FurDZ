

import furhatos.nlu.Intent

class QRCode_Ja : Intent() {
    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf(
            "QR-Code", "Ich habe einen QR-Code", "QRCode", "Kuh Arr", "QR", "Qa",
            "QR Code", "I have a QR Code", "QRCode", "Cow Arr","I have a 2-hour code","2 hour code","que Ar","quare kod var", "Bar", "var", "korkum var", "var herkod var","korkum var","var quare kod var","Evet",
            "QR kodu", "QR kodum var","Am un cod QR","Cod QR","q r code","I have a coupon codes",

            "nlu.Ja", "Genau", "Steinau", "Richtig", "Korrekt", "So ist es", "Das ist richtig",
            "Yes", "Auf jeden Fall", "Ganz Genau", "nlu.Ja, so ist es", "Genau richtig", "ja, bin ich",
            "ja, das ist wahr", "Jap","habt ihr einen", "ja", "doch", "jawohl", "ja klar", "aber sicher",
            "sicher doch", "klar", "Janna", "Pia", "Peer", "Jara", "zu holen", "zu bringen",
            "ja ich bringe jemanden", "ja ich hole jemanden", "nlu.Ja, stimmt", "Das stimmt", "Exakt",
            "zu bringen ja", "nia","ya",

            "Exactly", "Correct", "That's it", "That's right",
            "Yes", "Absolutely", "Quite Right", "Exactly Right", "Yes, I am",
            "yes, that's right", "yup", "do you have one", "yes", "yes you do", "yes sure", "but sure",
            "sure you do", "sure",
            "yes I'll bring someone", "yes I'll get someone",

            "Evet", "Kesinlikle", "Oldukça Doğru",  "Tam Olarak Doğru", "Evet, öyleyim", "evet, bu doğru", "evet", "var mı",  "evet var", "elbette var",
            "evet birini getireceğim", "evet birini getireceğim",  "Bu doğru", "Kesinlikle",
            "evet getirmek için","Mahallesi","Refika","geçirmeye", "almaya","almaya almaya geldim",

            "Exact", "Corect", "Asta este",
            "Da", "Absolut", "Destul de corect", "Exact", "Da, așa este",
            "da, așa este", "da", "ai una", "da, ai", "da, sigur", "dar sigur",
            "sigur că ai", "sigur", "da",
            "da, o să aduc pe cineva", "da, o să aduc pe cineva"
        )
    }
}