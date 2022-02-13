package codeWriter.subroutines.branching

object If : Branch {
    override fun create(label: String): String {
        val result = StringBuilder()
        result.appendLine("@SP")
        result.appendLine("AM=M-1")
        result.appendLine("D=M")
        result.appendLine("@$label")
        result.appendLine("D;JNE")
        return result.toString()
    }
}