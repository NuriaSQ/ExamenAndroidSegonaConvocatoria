package cat.itic.examensegonaconvocatoria.models

data class Llibre(
    val id: Int,
    val titol: String,
    val autor: String,
    val genere: Genere,
    val any: Int,
    val estat: Estat
)

sealed class Genere(val nom: String) {
    object Novella : Genere("Novel·la")
    object Assaig : Genere("Assaig")
    object Comic : Genere("Còmic")
}

sealed class Estat(val nom: String) {
    object PerLlegir : Estat("Per llegir")
    object Llegint : Estat("Llegint")
    object Llegit : Estat("Llegit")
}

