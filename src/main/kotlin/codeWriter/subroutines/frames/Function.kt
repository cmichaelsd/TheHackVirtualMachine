package codeWriter.subroutines.frames

import codeWriter.subroutines.branching.Label

object Function {
    fun create(functionName: String, nVars: Int): String {
        val result = StringBuilder()
        result.append(Label.create(functionName))
        for (i in 0 until nVars) {
            result.appendLine("@SP")
            result.appendLine("A=M")
            result.appendLine("M=0")
            result.appendLine("@SP")
            result.appendLine("M=M+1")
        }
        return result.toString()
    }
}