package codeWriter.subroutines.frames

import codeWriter.subroutines.branching.GoTo
import codeWriter.subroutines.branching.Label
import codeWriter.subroutines.stack.StackImpl
import java.util.concurrent.atomic.AtomicInteger

class Call(private val sequence: AtomicInteger) {
    fun create(functionName: String, nArgs: Int): String {
        val returnLabel = "RETURN.${sequence.incrementAndGet()}"

        val result = StringBuilder()
        val pushPairs = listOf(
            Pair("@$returnLabel", "D=A"),
            Pair("@LCL",          "D=M"),
            Pair("@ARG",          "D=M"),
            Pair("@THIS",         "D=M"),
            Pair("@THAT",         "D=M")
        )

        for (pair in pushPairs) {
            // PUSH
            result.appendLine(pair.first)
            result.appendLine(pair.second)
            setDataToMemory(result)
            StackImpl.incrementStackPointer(result)
        }

        // ARG = SP - nArgs - 5
        result.appendLine("@SP")
        result.appendLine("D=M")
        result.appendLine("@$nArgs")
        result.appendLine("D=D-A")
        result.appendLine("@5")
        result.appendLine("D=D-A")
        result.appendLine("@ARG")
        result.appendLine("M=D")

        // LCL = SP
        result.appendLine("@SP")
        result.appendLine("D=M")
        result.appendLine("@LCL")
        result.appendLine("M=D")

        result.append(GoTo.create(functionName))
        result.append(Label.create(returnLabel))
        return result.toString()
    }

    private fun setDataToMemory(stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@SP")
        stringBuilder.appendLine("A=M")
        stringBuilder.appendLine("M=D")
    }
}