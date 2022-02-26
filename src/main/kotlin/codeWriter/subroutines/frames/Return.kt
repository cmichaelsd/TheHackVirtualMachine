package codeWriter.subroutines.frames

object Return {
    fun create(): String {
        val result = StringBuilder()

        // FRAME = LCL
        result.appendLine("@LCL")
        result.appendLine("D=M")
        result.appendLine("@R13")
        result.appendLine("M=D")

        // RETURN = *(FRAME-5)
        result.appendLine("@5")
        result.appendLine("D=D-A")
        result.appendLine("A=D")
        result.appendLine("D=M")
        result.appendLine("@R14")
        result.appendLine("M=D")

        // *ARG = POP
        result.appendLine("@SP")
        result.appendLine("M=M-1")
        result.appendLine("A=M")
        result.appendLine("D=M")
        result.appendLine("@ARG")
        result.appendLine("A=M")
        result.appendLine("M=D")

        // SP = ARG+1
        result.appendLine("@ARG")
        result.appendLine("D=M+1")
        result.appendLine("@SP")
        result.appendLine("M=D")

        val pairAssign = listOf(
            Pair("@1", "@THAT"),
            Pair("@2", "@THIS"),
            Pair("@3", "@ARG"),
            Pair("@4", "@LCL")
        )

        for (pair in pairAssign) {
            result.appendLine("@R13")
            result.appendLine("D=M")
            result.appendLine(pair.first)
            result.appendLine("D=D-A")
            result.appendLine("A=D")
            result.appendLine("D=M")
            result.appendLine(pair.second)
            result.appendLine("M=D")
        }

        // JMP TO CALLER
        result.appendLine("@R14")
        result.appendLine("A=M")
        result.appendLine("0;JMP")
        return result.toString()
    }
}