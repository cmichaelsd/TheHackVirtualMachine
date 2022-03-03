package codeWriter.subroutines.stack

import segment.Segment

abstract class AbstractOperation(private val fileName: String) : Operation {
    fun getAddress(segment: String, index: Int): String {
        return when(segment) {
            "pointer" -> "R${3 + index}"
            "static"  -> "$fileName.$index"
            "temp"    -> "R${5 + index}"
            else      -> ""
        }
    }

    fun setAddressToContext(segment: String, index: Int, stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@$index")
        stringBuilder.appendLine("D=A")
        stringBuilder.appendLine("@${Segment.getTranslation(segment)}")
        stringBuilder.appendLine("A=M")
    }
}