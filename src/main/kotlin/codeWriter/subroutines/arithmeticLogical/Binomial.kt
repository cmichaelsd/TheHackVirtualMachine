package codeWriter.subroutines.arithmeticLogical

object Binomial: Operation {
    override fun create(operation: String): String {
        val result = StringBuilder()
        result.appendLine("@SP")
        result.appendLine("AM=M-1")
        result.appendLine("D=M")
        result.appendLine("@SP")
        result.appendLine("AM=M-1")
        result.appendLine(operation)
        result.appendLine("@SP")
        result.appendLine("M=M+1")
        return result.toString()
    }
}