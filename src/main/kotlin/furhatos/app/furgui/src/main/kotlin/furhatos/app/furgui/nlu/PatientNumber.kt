import furhatos.nlu.Intent

// Funktioniert ähnlich wie die English Klasse, um Intentionen zu definieren.
class PatientNumber : Intent() {

    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf("Patientennummer", "Patient", "nummer", "patient", "number", "ich habe eine Patientennummer", "Patentnummer","Objektnummer",

            "patient number", "patient", "nummer", "patient", "number", "I have a patient number",

            "hasta numarası", "hasta", "nummer", "hasta", "numara", "Hasta numaram var",

            "număr de pacient", "pacient", "pacient", "număr","numa", "am un număr de pacient")
    }

}