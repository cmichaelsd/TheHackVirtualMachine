package codeWriter.subroutines.branching

object GoTo : Branch {
    override fun create(label: String): String {
        val result = StringBuilder()
        result.appendLine("@$label")
        result.appendLine("0;JMP")
        return result.toString()
    }
}