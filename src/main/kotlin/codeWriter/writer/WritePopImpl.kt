package codeWriter.writer

import segment.Segment

class WritePopImpl(fileName: String) :
    AbstractPushPop(fileName),
    WritePushPop {
    override fun write(segment: String, index: Int) {
        when(segment) {
            "pointer",
            "static",
            "temp" -> {
                val address = getAddress(segment, index)
                decrementStackPointer()
                arrayOf("@$address", "M=D")
            }
            "local",
            "argument",
            "this",
            "that" -> {
                setAddressToContext(segment, index)
                arrayOf("D=D+A", "@${Segment.getTranslation(segment)}", "M=D")
                decrementStackPointer()
                arrayOf("@${Segment.getTranslation(segment)}", "A=M", "M=D")
                setAddressToContext(segment, index)
                arrayOf("D=A-D", "@${Segment.getTranslation(segment)}", "M=D")
            }
        }
    }

    private fun decrementStackPointer() {
        arrayOf("@SP", "M=M-1", "AD=M")
    }
}