package segment

import parser.Parsable

object Segment : Parsable {
    override val validCommand: Set<String>  = setOf(
        "argument",
        "local",
        "static",
        "constant",
        "this",
        "that",
        "pointer",
        "temp"
    )

    /**
     * Returns the assembly translation of a segment name.
     *
     * @param  segment the string which represents a segment
     * @return String?
     */
    fun getTranslation(segment: String): String? {
        val translations = mapOf(
            Pair("local",    "LCL"),
            Pair("argument", "ARG"),
            Pair("this",     "THIS"),
            Pair("that",     "THAT")
        )
        return translations[segment]
    }
}