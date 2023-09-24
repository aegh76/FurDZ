package furhatos.app.furgui.flow.helper


import furhatos.records.User
import java.io.BufferedReader
import java.io.IOException
import furhatos.flow.kotlin.Furhat
import jcifs.CIFSContext
import jcifs.config.PropertyConfiguration
import jcifs.context.BaseContext
import jcifs.smb.NtlmPasswordAuthenticator
import jcifs.smb.SmbFile
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter


// Diese Funktion wurde speziell für das Format des Patientenbelegungsplans in csv entwickelt.
// Es ist wichtig, das Format dieses Plans zu kennen, um den Code vollständig zu verstehen.

fun readExcel(benutzer: User,
              furhat: Furhat,
              username: String,
              password: String,
              networkDrivePath: String)
{


    // Konfiguration für den Netzwerkzugriff
    val config = PropertyConfiguration(System.getProperties())
    val context = BaseContext(config)
    val credentials = NtlmPasswordAuthenticator(username, password)
    val cifsContext: CIFSContext = context.withCredentials(credentials)


    // Verbindung zum Netzwerklaufwerk herstellen
    val file = SmbFile(networkDrivePath, cifsContext)
    val inputStream = file.inputStream

    // Trennzeichen für die Daten in der csv-Datei
    val separator = ";"

    // Patientennummer aus dem Benutzerobjekt holen
    var searchNum: String = benutzer.get("Patientennummer").toString()

    // Standardwert für den Raum setzen
    benutzer.put("raum", null)

    // Aktuelles Datum im benötigten Format holen
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val currentDate = LocalDate.now()
    val formattedDate = currentDate.format(formatter)




    try {
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            // Lese jede Zeile der Datei
            var line: String?
            // Überspringe die erste Zeile (Kopfzeile)
            reader.readLine()


            while (reader.readLine().also { line = it } != null) {

                // Zeile in einzelne Daten aufteilen
                val data = line!!.split(separator)
                // Verschiedene Daten aus der Zeile extrahieren
                val currentPatNr = data[1].trim()
                val raum = data[3].trim()
                val platz1 = data[4].trim()
                val Beginn =data[5].substring(11,16)
                val Terminbeginn = data[5].substring(0, 10)


                // Überprüfe, ob die aktuelle Zeile die gesuchte Patientennummer und das aktuelle Datum enthält
                if (currentPatNr == searchNum && formattedDate == Terminbeginn) {

                    // Weitere Daten extrahieren und im Benutzerobjekt speichern
                    val raum2 = raum.substring(raum.length - 1)
                    val platz = data[4].substring( platz1.length - 1)
                    val Ende = data[6].substring(11,16)
                    val Vorname = data[7]
                    val Nachname = data[8]
                    val Name = Vorname + " " + Nachname

                    // Daten im Benutzerobjekt speichern
                    benutzer.put("raum", raum2)
                    benutzer.put("platz", platz)
                    benutzer.put("dialysebeginn", Beginn)
                    benutzer.put("dialyseende", Ende)
                    benutzer.put("name", Name)
                    benutzer.put ("datum", Terminbeginn)

                }
            }
        }
    } catch (e: IOException) {
        // Bei einem Fehler die Fehlermeldung ausgeben
        e.printStackTrace()
    }
}


