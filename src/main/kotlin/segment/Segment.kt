package segment

import parser.Parsable

object Segment : Parsable {
    override val validCommand: List<String>  = listOf(
        "argument",
        "local",
        "static",
        "constant",
        "this",
        "that",
        "pointer",
        "temp"
    )
}