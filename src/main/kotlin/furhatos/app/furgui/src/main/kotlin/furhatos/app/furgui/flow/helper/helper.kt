package furhatos.app.furgui.flow.helper


import PatientNumberRO_TU
import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.HybridBinarizer
import furhatos.app.furgui.validation.validationNumber
import furhatos.app.furgui.validation.validationNumberRO_TU
import furhatos.flow.kotlin.*
import furhatos.skills.UserManager.current
import furhatos.util.Language
import org.zeromq.SocketType
import org.zeromq.ZContext
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO
import furhatos.flow.kotlin.Furhat
import furhatos.records.User



// In dieser Datei sind alle Hilfsfunktionen definiert, die für die Interaktion mit Furhat benötigt werden.


/*  Die Funktion 'furhatask' ermöglicht es Furhat, eine Frage in einer von vier unterstützten Sprachen zu stellen.
    furhat Dies ist unser Zugriffspunkt für Furhat. Mit 'furhat' können wir Furhat Anweisungen geben, wie er sprechen oder auf den Benutzer reagieren soll.
    'englishText': Text der Frage auf Englisch. 'turkishText': Text der Frage auf Türkisch.  'romanianText': Text der Frage auf Rumänisch. 'germanText': Text der Frage auf Deutsch.
    'sprache': Gibt die aktuell gewählte Sprache an.
    Abhängig von der gewählten Sprache wird Furhat die entsprechende Frage stellen.
 */

fun furhatask (furhat:Furhat, englishText: String, turkishText: String, romanianText: String, germanText: String, sprache: Language) {
    if (sprache == Language.ENGLISH_US) {
        furhat.ask(text=englishText)
    }
    if (sprache == Language.TURKISH) {
        furhat.ask(text=turkishText)
    }
    if (sprache == Language.ROMANIAN) {
        furhat.ask(text=romanianText)
    }
    if (sprache == Language.GERMAN) {
        furhat.ask(text=germanText)
    }

}

// Funktion, um Furhat eine Nachricht in der vom Benutzer gewählten Sprache sagen zu lassen. Siehe furhatask
fun furhatsay (furhat:Furhat, englishText: String, turkishText: String, romanianText: String, germanText: String, sprache: Language) {
    if (sprache == Language.ENGLISH_US) {
        furhat.say(text=englishText)
    }
    if (sprache == Language.TURKISH) {
        furhat.say(text=turkishText)
    }
    if (sprache == Language.ROMANIAN) {
        furhat.say(text=romanianText)
    }
    if (sprache == Language.GERMAN) {
        furhat.say(text=germanText)
    }
}

/*  Mit der Funktion 'getDigits' bitten wir Furhat, den Benutzer nach seiner Patientennummer zu fragen.
    Aber es gibt einen besonderen Punkt: Wir möchten, dass Furhat nur Zahlen als Antwort akzeptiert.
    Sie nutzt die furhatos.nlu.common.Number-Bibliothek, die Ziffern für Haupt-Sprachen wie Englisch und Deutsch enthält.
    Wenn der Benutzer also Buchstaben oder andere Zeichen sagt, werden diese ignoriert.
    Benutzer: User Hier speichern wir Informationen, die Furhat über den Benutzer erfährt, z.B. die Sprache des Benutzers oder seine Patientennummer.
    furhat: Furhat Wiederum unsere Zugriffspunkt für Furhat, mit der wir ihm Anweisungen geben können. */
fun getDigits(Benutzer: User, furhat: Furhat) {

    // Generiert eine Nachricht basierend auf der gewählten Sprache des Benutzers.
    val sprachNachricht = when (Benutzer.get("sprache") as Language) {
        Language.GERMAN -> "Sagen Sie mir bitte die Patientennummer Ziffer für Ziffer"
        Language.ENGLISH_US -> "Please tell me the patient number digit by digit"
        Language.TURKISH -> "Lütfen bana hasta numarasını tek tek söyleyin"
        Language.ROMANIAN-> "Vă rog să-mi spuneți numărul pacientului, cifră cu cifră"
        else -> "Wie lautet die Patientennummer Ihres Kunden"
    }

    // Furhat fragt nach der Patientennummer in der Bibliothek.
    furhat.askFor<furhatos.nlu.common.Number>(
        sprachNachricht,
        timeout = 15000, // Furhat wird 15 Sekunden auf eine Antwort warten.
        endSil = 5000   // Furhat wird eine Antwort als beendet betrachten, wenn 5 Sekunden Stille herrschen.
    ) {
        onResponse<furhatos.nlu.common.Number> {
            furhat.attend(user = current)
            // Entfernt Leerzeichen aus der eingegebenen Patientennummer.
            val x: String = it.text.toString().replace(" ".toRegex(), "")
            // Filtert Buchstaben aus und behält nur die Ziffern
            val resultx: String = x.filter { it.isDigit() }
            // Speichert die bereinigte Patientennummer im Benutzerobjekt
            Benutzer.put("Patientennummer", resultx)
            // Leitet weiter.
            goto(validationNumber)
        }
    }
}


/*
 * Die 'patientnumberRO_TU'-Funktion fragt den Benutzer ebenfalls nach seiner Patientennummer.
 * Sie verwendet jedoch eine spezielle Kotlin-Klasse namens 'PatientennummerRO_TU', die Patientennummern als Strings enthält.
 * Diese Klasse wurde speziell für rumänische und türkische Patientennummern erstellt, da diese Sprachen nicht in der furhatos.nlu.common.Number-Bibliothek vorhanden sind.
  */

fun patientnumberRO_TU(Benutzer: User, furhat: Furhat) {

    val sprachNachricht = when (Benutzer.get("sprache") as Language) {
        Language.GERMAN -> "Sagen Sie mir bitte die Patientennummer Ziffer für Ziffer"
        Language.ENGLISH_US -> "Please tell me the patient number digit by digit"
        Language.TURKISH -> "Lütfen bana hasta numarasını tek tek söyleyin"
        Language.ROMANIAN-> "Vă rog să-mi spuneți numărul pacientului, cifră cu cifră"
        else -> "Wie lautet die Patientennummer Ihres Kunden"
    }
    // Furhat fragt nach der Patientennummer in der Kotlin Klasse.
    furhat.askFor<PatientNumberRO_TU>(sprachNachricht){

    onResponse {
        val patientennumber: String = it.text.toString().replace(" ".toRegex(), "")
        Benutzer.put("Patientennummer", patientennumber)
        goto(validationNumberRO_TU)
    }

    }
}



// Diese Funktion überprüft, ob ein übergebenes Bild einen QR-Code enthält.
fun containsQRCode(image: BufferedImage): Boolean {
    // Erstelle eine Quelle für die Helligkeitsinformationen des Bildes.
    val luminanceSource = RGBLuminanceSource(image.width, image.height, getPixels2(image))
    // Erstelle ein binäres Bitmap-Bild aus der Helligkeitsquelle.
    // Ein Bitmap-Bild ist eine Art von Bild, das aus Pixeln besteht. Hier wird ein binäres Bitmap-Bild
    // aus der Helligkeitsquelle erstellt, wobei jedes Pixel entweder schwarz oder weiß ist.
    val binaryBitmap = BinaryBitmap(HybridBinarizer(luminanceSource))
    // Ein Werkzeug zum Lesen verschiedener Barcode-Formate.
    val reader = MultiFormatReader()

    return try {
        // Versuche, einen QR-Code im binären Bitmap-Bild zu erkennen.
        reader.decode(binaryBitmap)
        // Wenn ein QR-Code erkannt wurde, gebe 'true' zurück.
        true
    } catch (e: Exception) {
        // Wenn kein QR-Code erkannt wurde, gebe 'false' zurück
        false
    }
}

// Diese Funktion holt die Pixelinformationen aus einem Bild.
fun getPixels2(image: BufferedImage): IntArray {
    // Hole die Breite und Höhe des Bildes.
    val width = image.width
    val height = image.height
    // Erstelle ein Array, um die Pixelinformationen zu speichern.
    // Ein Array ist eine geordnete Sammlung von Elementen. Hier wird ein Array erstellt,
    // um die Pixelinformationen des Bildes zu speichern.
    val pixels = IntArray(width * height)
    // Fülle das Pixel-Array mit den Pixelinformationen des Bildes.
    image.getRGB(0, 0, width, height, pixels, 0, width)
    // Gebe das Pixel-Array zurück.
    return pixels
}

// Ein Zähler für QR-Code-Erkennungsversuche.
var qrcodecounter = 0

// Diese Funktion empfängt ein Bild von einem externen Socket.
fun captureImageFromSocket(benutzer: User) {

    // Erhöhe den QR-Code-Erkennungsversuchszähler.
    qrcodecounter++

    // Erstelle einen Kontext für den Socket. Erstelle einen Abonnenten-Socket.
    val context = ZContext()
    val subscriber = context.createSocket(SocketType.SUB)

    // Verbinde den Socket mit dem ZMQ.SUB-Socket THM
   // subscriber.connect("tcp://10.198.3.150:3000")


   // Verbinde den Socket mit dem ZMQ.SUB-Socket DZ
    subscriber.connect("tcp://10.203.31.51:3000")


    // Setze den Filter auf leeren String, um alle Nachrichten zu empfangen
    subscriber.subscribe("".toByteArray())


    // Empfange eine Nachricht
    val message = subscriber.recv(0)

    // Schließe den Socket und den Kontext
    subscriber.close()
    context.close()

    // Überprüfe, ob eine Nachricht empfangen wurde
    if (message != null) {
        // Verarbeite das empfangene Bild
        val imageInput = ImageIO.read(ByteArrayInputStream(message))
        // Überprüfe, ob das Bild einen QR-Code enthält.
        if (!containsQRCode(imageInput)) {
            // Wenn kein QR-Code erkannt wurde, versuche es erneut nach einer kurzen Pause.

            Thread.sleep(1000) // Füge eine Verzögerung von einer Sekunde ein
            if(qrcodecounter == 10){
                return
            }
            else {
                captureImageFromSocket(benutzer)
            }
        } else {
            // Wenn ein QR-Code erkannt wurde, verarbeite die Informationen.
            val luminanceSource = RGBLuminanceSource(imageInput.width, imageInput.height, getPixels2(imageInput))
            val binaryBitmap = BinaryBitmap(HybridBinarizer(luminanceSource))
            val reader = MultiFormatReader()
            val result = reader.decode(binaryBitmap)

            println("QR Code Text: ${result.text}")
            val barcodeText: String = result.text
            // Speichere den erkannten QR-Code-Text im Benutzerobjekt.
            benutzer.put("QR Code Text", barcodeText)
        }
    } else {
        // Keine Nachricht empfangen - handle den Fall entsprechend
        // Zum Beispiel eine Fehlermeldung ausgeben oder weitere Maßnahmen ergreifen
    }
}
