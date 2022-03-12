package codeWriter.subroutines.stack

import segment.Segment

object StackImpl : Stack {
    override fun incrementStackPointer(stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@SP")
        stringBuilder.appendLine("M=M+1")
    }

    override fun decrementStackPointer(stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@SP")
        stringBuilder.appendLine("AM=M-1")
    }

    override fun push(fileName: String, segment: String, index: Int): String {
        val result = StringBuilder()
        when(segment) {
            "pointer",
            "static",
            "temp" -> {
                result.appendLine("@${getAddress(fileName, segment, index)}")
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
        result.appendLine("@SP")
        result.appendLine("A=M")
        result.appendLine("M=D")
        incrementStackPointer(result)
        return result.toString()
    }

    override fun pop(fileName: String, segment: String, index: Int): String {
        val result = StringBuilder()
        when(segment) {
            "pointer",
            "static",
            "temp" -> {
                decrementStackPointer(result)
                result.appendLine("D=M")
                result.appendLine("@${getAddress(fileName, segment, index)}")
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

    private fun getAddress(fileName: String, segment: String, index: Int): String {
        return when(segment) {
            "pointer" -> "R${3 + index}"
            "static"  -> "$fileName.$index"
            "temp"    -> "R${5 + index}"
            else      -> ""
        }
    }

    private fun setAddressToContext(segment: String, index: Int, stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@$index")
        stringBuilder.appendLine("D=A")
        stringBuilder.appendLine("@${Segment.getTranslation(segment)}")
        stringBuilder.appendLine("A=M")
    }
}