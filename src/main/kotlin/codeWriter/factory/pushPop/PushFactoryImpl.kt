package codeWriter.factory.pushPop

class PushFactoryImpl(fileName: String) :
    AbstractPushPop(fileName),
    PushPopFactory {
    override fun create(segment: String, index: Int): List<String> {
        val arrayList = arrayListOf<String>()
        when(segment) {
            "pointer",
            "static",
            "temp" -> {
                val address = getAddress(segment, index)
                arrayList.addAll(listOf("@$address", "D=M"))
            }
            "constant" -> arrayList.addAll(listOf("@$index", "D=A"))
            "local",
            "argument",
            "this",
            "that" -> {
                arrayList.addAll(setAddressToContext(segment, index))
                arrayList.addAll(listOf("D=D+A", "A=D", "D=M"))
            }
        }
        arrayList.addAll(setStackPointerMemoryToAddressAndDataToMemory())
        arrayList.addAll(incrementStackPointer())
        return arrayList.toList()
    }

    private fun incrementStackPointer(): List<String> {
        return listOf("@SP", "M=M+1")
    }
}