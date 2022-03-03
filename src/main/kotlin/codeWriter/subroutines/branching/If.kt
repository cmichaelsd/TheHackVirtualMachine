package codeWriter.subroutines.branching

import codeWriter.subroutines.stack.StackImpl

object If : Branch {
    override fun create(label: String): String {
        val result = StringBuilder()
        StackImpl.decrementStackPointer(result)
        result.appendLine("D=M")
        result.appendLine("@$label")
        result.appendLine("D;JNE")
        return result.toString()
    }
}