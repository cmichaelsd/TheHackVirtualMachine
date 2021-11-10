package codeWriter.writer

import segment.Segment

abstract class AbstractPushPop(private val fileName: String) {
    fun getAddress(segment: String, index: Int): String {
        return when(segment) {
            "pointer" -> if (index == 0) "THIS" else "THAT"
            "static" -> "$fileName.$index"
            "temp" -> "${5 + index}"
            else -> ""
        }
    }

    fun setAddressToContext(segment: String, index: Int) {
        arrayOf(
            "@$index",
            "D=A",
            "@${Segment.getTranslation(segment)}",
            "A=M"
        )
    }

    fun setStackPointerMemoryToAddressAndDataToMemory() {
        arrayOf("@SP", "A=M", "M=D")
    }
}