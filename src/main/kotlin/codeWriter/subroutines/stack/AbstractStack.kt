package codeWriter.subroutines.stack

import segment.Segment

abstract class AbstractStack(private val fileName: String) {
    fun getAddress(segment: String, index: Int): String {
        return when(segment) {
            "pointer" -> "R${3 + index}" // UNSURE OF BASE HERE
            "static"  -> "$fileName.$index"
            "temp"    -> "R${5 + index}"
            else      -> ""
        }
    }

    fun setAddressToContext(segment: String, index: Int): List<String> {
        return listOf(
            "@$index",
            "D=A",
            "@${Segment.getTranslation(segment)}",
            "A=M"
        )
    }

    fun setStackPointerMemoryToAddressAndDataToMemory(): List<String> {
        return listOf("@SP", "A=M", "M=D")
    }
}