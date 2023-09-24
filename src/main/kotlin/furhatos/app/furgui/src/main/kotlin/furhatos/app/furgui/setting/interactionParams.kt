package furhatos.app.furgui.setting

// Diese Konstante legt die maximale Anzahl von Personen fest, die Furhat gleichzeitig als Benutzer erkennen wird.
// Personen, die innerhalb eines Radius von 1 Meter erkannt werden, werden als Benutzer betrachtet.
// Bezieht sich auf die Richtlinien im "Init"-Zustand die hier festgelegt wurden.
const val MAX_NUMBER_OF_USERS = 2 // Max amount of people that Furhat will recognize as users simultaneously
const val DISTANCE_TO_ENGAGE = 1.0 // Min distance for people to be recognised as users
