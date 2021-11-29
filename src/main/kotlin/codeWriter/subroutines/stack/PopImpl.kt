package codeWriter.subroutines.stack

import segment.Segment

class PopImpl(fileName: String) :
    AbstractStack(fileName),
    Stack {
    override fun create(segment: String, index: Int): List<String> {
        val arrayList = arrayListOf<String>()
        when(segment) {
            "pointer",
            "static",
            "temp" -> {
                val address = getAddress(segment, index)
                arrayList.addAll(decrementStackPointer())
                arrayList.addAll(listOf("@$address", "M=D"))
            }
            "local",
            "argument",
            "this",
            "that" -> {
                arrayList.addAll(setAddressToContext(segment, index))
                arrayList.addAll(listOf("D=D+A", "@${Segment.getTranslation(segment)}", "M=D"))
                arrayList.addAll(decrementStackPointer())
                arrayList.addAll(listOf("@${Segment.getTranslation(segment)}", "A=M", "M=D"))
                arrayList.addAll(setAddressToContext(segment, index))
                arrayList.addAll(listOf("D=A-D", "@${Segment.getTranslation(segment)}", "M=D"))
            }
        }
        return arrayList.toList()
    }

    private fun decrementStackPointer(): List<String> {
        return listOf("@SP", "AM=M-1", "D=M")
    }
}