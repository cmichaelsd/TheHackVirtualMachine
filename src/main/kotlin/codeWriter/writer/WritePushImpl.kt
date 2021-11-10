package codeWriter.writer

class WritePushImpl(fileName: String) :
    AbstractPushPop(fileName),
    WritePushPop {
    override fun write(segment: String, index: Int) {
        when(segment) {
            "pointer",
            "static",
            "temp" -> {
                val address = getAddress(segment, index)
                arrayOf("@$address", "D=M")
            }
            "constant" -> arrayOf("@$index", "D=A")
            "local",
            "argument",
            "this",
            "that" -> {
                setAddressToContext(segment, index)
                arrayOf("D=D+A", "A=D", "D=M")
            }
        }
        setStackPointerMemoryToAddressAndDataToMemory()
        incrementStackPointer()
    }

    private fun incrementStackPointer() {
        arrayOf("@SP", "M=M+1")
    }
}