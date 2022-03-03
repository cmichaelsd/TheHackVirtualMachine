package codeWriter.subroutines.frames

import codeWriter.subroutines.branching.Label
import codeWriter.subroutines.stack.StackImpl

object Function {
    fun create(functionName: String, nVars: Int): String {
        val result = StringBuilder()
        result.append(Label.create(functionName))
        for (i in 0 until nVars) {
            result.appendLine("@SP")
            result.appendLine("A=M")
            result.appendLine("M=0")
            StackImpl.incrementStackPointer(result)
        }
        return result.toString()
    }
}