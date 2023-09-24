import furhatos.nlu.Intent

/*
  Diese Klasse dient dazu, Patientennummern zu erkennen und zu verarbeiten.
  Da die furhatos.nlu.common.Number-Bibliothek nicht die Sprachen Türkisch und Rumänisch unterstützt,
  werden die Patientennummern hier manuell definiert. Der Benutzer gibt die Zahlen ziffernweise ein,
  und diese werden dann in der helper.kt zusammengeführt und mit den in dieser Klasse definierten Nummern verglichen.
  Es ist wichtig, diese Liste regelmäßig zu aktualisieren, um alle gültigen Patientennummern abzudecken.
 */
class PatientNumberRO_TU : Intent() {

    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf("53244",
            "47592",
            "52814",
            "47794",
            "51594",
            "58123",
            "57478",
            "55549",
            "50656",
            "54342",
            "54518",
            "56580",
            "52109",
            "49936",
            "55637",
            "51526",
            "59060",
            "55255",
            "56784",
            "57656",
            "48763",
            "48044",
            "58264",
            "57071",
            "57950",
            "54152",
            "48695",
            "50291",
            "54073",
            "56049",
            "50518",
            "53506",
            "3508",
            "42860",
            "47758",
            "58050",
            "58834",
            "52627",
            "52759",
            "14672",
            "56258",
            "56238",
            "54276",
            "55254",
            "50793",
            "54517",
            "55670",
            "54941",
            "54758",
            "48299",
            "58740",
            "52492",
            "52076",
            "50152",
            "50778",
            "463",
            "55608",
            "51741",
            "53702",
            "54847",
            "50827",
            "55316",
            "53763",
            "55288",
            "55393",
            "53128",
            "53938",
            "40570",
            "54817",
            "49992",
            "52184",
            "55810",
            "55047",
            "50383",
            "47787",
            "52467",
            "57789",
            "47915",
            "51407",
            "55092",
            "50409",
            "41212",
            "49978",
            "58556",
            "46090",
            "29454",
            "54019",
            "58402",
            "52550",
            "58659",
            "56175",
            "53068",
            "49671",
            "48471",
            "21667",
            "54340",
            "49564",
            "51108",
            "56300",
            "49984",
            "49478",
            "58119",
            "58018",
            "48656",
            "5321",
            "53249",
            "26343",
            "18888",
            "47767",
            "50965",
            "48899",
            "26048",
            "51027",
            "47150",
            "55643",
            "57118",
            "58122",
            "56842",
            "50786",
            "57925",
            "58715",
            "57215",
            "48601",
            "55675",
            "51382",
            "56237",
            "57084",
            "51824",
            "56760",
            "10729",
            "52345",
            "53897",
            "55621",
            "52688",
            "58454",
            "52921",
            "57518",
            "55062",
            "54861",
            "45406",
            "57831",
            "58149",
            "49657",
            "56994",
            "56942",
            "55220",
            "56014",
            "58917",
            "56866",
            "55192",
            "54918",
            "54928",
            "21033",
            "58706",
            "58780",
            "55540",
            "58311",
            "47996",
            "55575",
            "50533",
            "52537",
            "49426",
            "53575",
            "47760",
            "55323",
            "52063",
            "50071",
            "56058",
            "25090",
            "55169",
            "50370",
            "57026",
            "47621",
            "53237",
            "57668",
            "52355",
            "49683",
            "53977",
            "42962",
            "47884",
            "40184",
            "58483",
            "51327",
            "52073","13",
            "47596"
        )
    }

}