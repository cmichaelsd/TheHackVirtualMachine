package codeWriter.subroutines.stack

import segment.Segment
import kotlin.text.StringBuilder

class PopImpl(fileName: String) : AbstractOperation(fileName) {
    override fun create(segment: String, index: Int): String {
        val result = StringBuilder()
        when(segment) {
            "pointer",
            "static",
            "temp" -> {
                StackImpl.decrementStackPointer(result)
                result.appendLine("D=M")
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
                StackImpl.decrementStackPointer(result)
                result.appendLine("D=M")
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
}