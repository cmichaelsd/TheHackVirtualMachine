package codeWriter.subroutines.arithmeticLogical

import codeWriter.subroutines.stack.StackImpl

object Binomial: Operation {
    override fun create(operation: String): String {
        val result = StringBuilder()
        StackImpl.decrementStackPointer(result)
        result.appendLine("D=M")
        StackImpl.decrementStackPointer(result)
        result.appendLine(operation)
        StackImpl.incrementStackPointer(result)
        return result.toString()
    }
}