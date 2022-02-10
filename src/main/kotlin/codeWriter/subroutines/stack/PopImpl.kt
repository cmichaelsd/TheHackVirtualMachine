package codeWriter.subroutines.stack

import segment.Segment

class PopImpl(fileName: String) :
    AbstractStack(fileName),
    Stack {
    override fun create(segment: String, index: Int): List<String> {
        val result = mutableListOf<String>()
        when(segment) {
            "pointer",
            "static",
            "temp" -> {
                val address = getAddress(segment, index)
                result.addAll(decrementStackPointer())
                result.addAll(listOf("@$address", "M=D"))
            }
            "local",
            "argument",
            "this",
            "that" -> {
                result.addAll(setAddressToContext(segment, index))
                result.addAll(listOf("D=D+A", "@${Segment.getTranslation(segment)}", "M=D"))
                result.addAll(decrementStackPointer())
                result.addAll(listOf("@${Segment.getTranslation(segment)}", "A=M", "M=D"))
                result.addAll(setAddressToContext(segment, index))
                result.addAll(listOf("D=A-D", "@${Segment.getTranslation(segment)}", "M=D"))
            }
        }
        return result
    }

    private fun decrementStackPointer(): List<String> {
        return listOf("@SP", "AM=M-1", "D=M")
    }
}