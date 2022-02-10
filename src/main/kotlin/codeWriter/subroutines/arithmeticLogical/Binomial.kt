package codeWriter.subroutines.arithmeticLogical

object Binomial: Operation {
    override fun create(operation: String): List<String> {
        return listOf(
            "@SP",
            "AM=M-1",
            "D=M",
            "@SP",
            "AM=M-1",
            operation,
            "@SP",
            "M=M+1"
        )
    }
}