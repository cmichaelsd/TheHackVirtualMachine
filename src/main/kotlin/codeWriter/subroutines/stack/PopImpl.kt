package codeWriter.subroutines.stack

import segment.Segment
import kotlin.text.StringBuilder

class PopImpl(fileName: String) : AbstractStack(fileName), Stack {
    override fun create(segment: String, index: Int): String {
        val result = StringBuilder()

        when(segment) {
            "pointer",
            "static",
            "temp" -> {
                decrementStackPointer(result)
                result.appendLine("@${getAddress(segment, index)}")
                result.appendLine("M=D")
            }
            "local",
            "argument",
            "this",
            "that" -> {
                setAddressToContext(segment, index, result)
                result.appendLine("D=D+A")
                result.appendLine("@${Segment.getTranslation(segment)}")
                result.appendLine("M=D")
                decrementStackPointer(result)
                result.appendLine("@${Segment.getTranslation(segment)}")
                result.appendLine("A=M")
                result.appendLine("M=D")
                setAddressToContext(segment, index, result)
                result.appendLine("D=A-D")
                result.appendLine("@${Segment.getTranslation(segment)}")
                result.appendLine("M=D")
            }
        }

        return result.toString()
    }

    private fun decrementStackPointer(stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@SP")
        stringBuilder.appendLine("AM=M-1")
        stringBuilder.appendLine("D=M")
    }
}