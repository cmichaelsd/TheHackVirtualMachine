package codeWriter.subroutines.arithmeticLogical

import codeWriter.subroutines.branching.GoTo
import codeWriter.subroutines.branching.Label
import codeWriter.subroutines.stack.StackImpl
import java.util.concurrent.atomic.AtomicInteger

class Comparison(private val sequence: AtomicInteger) : Operation {
    override fun create(operation: String): String {
        sequence.set(sequence.get() + 1)

        val trueLabel  = "COMP.${sequence.get()}.TRUE"
        val falseLabel = "COMP.${sequence.get()}.FALSE"
        val endLabel   = "COMP.${sequence.get()}.END"

        val result = StringBuilder()
        StackImpl.decrementStackPointer(result)
        result.appendLine("D=M")
        StackImpl.decrementStackPointer(result)
        result.appendLine("D=M-D")
        result.appendLine("@$trueLabel")
        result.appendLine(operation)
        result.append(GoTo.create(falseLabel))
        result.append(Label.create(trueLabel))
        setAddressToStackPointer(result)
        result.appendLine("M=-1")
        StackImpl.incrementStackPointer(result)
        result.append(GoTo.create(endLabel))
        result.append(Label.create(falseLabel))
        setAddressToStackPointer(result)
        result.appendLine("M=0")
        StackImpl.incrementStackPointer(result)
        result.append(Label.create(endLabel))
        return result.toString()
    }

    private fun setAddressToStackPointer(stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@SP")
        stringBuilder.appendLine("A=M")
    }
}