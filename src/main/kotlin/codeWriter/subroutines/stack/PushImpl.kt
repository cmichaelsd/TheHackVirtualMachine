package codeWriter.subroutines.stack

import kotlin.text.StringBuilder

class PushImpl(fileName: String) : AbstractStack(fileName), Stack {
    override fun create(segment: String, index: Int): String {
        val result = StringBuilder()

        when(segment) {
            "pointer",
            "static",
            "temp" -> {
                result.appendLine("@${getAddress(segment, index)}")
                result.appendLine("D=M")
            }
            "constant" -> {
                result.appendLine("@$index")
                result.appendLine("D=A")
            }
            "local",
            "argument",
            "this",
            "that" -> {
                setAddressToContext(segment, index, result)
                result.appendLine("D=D+A")
                result.appendLine("A=D")
                result.appendLine("D=M")
            }
        }

        setStackPointerMemoryToAddressAndDataToMemory(result)
        incrementStackPointer(result)

        return result.toString()
    }

    private fun incrementStackPointer(stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@SP")
        stringBuilder.appendLine("M=M+1")
    }
}