package codeWriter.subroutines.stack

object StackImpl : Stack {
    override fun incrementStackPointer(stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@SP")
        stringBuilder.appendLine("M=M+1")
    }

    override fun decrementStackPointer(stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@SP")
        stringBuilder.appendLine("AM=M-1")
    }
}