package codeWriter.subroutines.arithmeticLogical

object Singular : Operation {
    override fun create(operation: String): String {
        val result = StringBuilder()
        result.appendLine("@SP")
        result.appendLine("AM=M-1")
        result.appendLine(operation)
        result.appendLine("@SP")
        result.appendLine("M=M+1")
        return result.toString()
    }
}