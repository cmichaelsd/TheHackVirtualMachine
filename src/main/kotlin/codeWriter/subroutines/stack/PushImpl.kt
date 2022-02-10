package codeWriter.subroutines.stack

class PushImpl(fileName: String) :
    AbstractStack(fileName),
    Stack {
    override fun create(segment: String, index: Int): List<String> {
        val result = mutableListOf<String>()
        when(segment) {
            "pointer",
            "static",
            "temp" -> {
                val address = getAddress(segment, index)
                result.addAll(listOf("@$address", "D=M"))
            }
            "constant" -> result.addAll(listOf("@$index", "D=A"))
            "local",
            "argument",
            "this",
            "that" -> {
                result.addAll(setAddressToContext(segment, index))
                result.addAll(listOf("D=D+A", "A=D", "D=M"))
            }
        }
        result.addAll(setStackPointerMemoryToAddressAndDataToMemory())
        result.addAll(incrementStackPointer())
        return result
    }

    private fun incrementStackPointer(): List<String> {
        return listOf("@SP", "M=M+1")
    }
}