package codeWriter.subroutines.arithmeticLogical

object Singular : Operation {
    override fun create(operation: String): List<String> {
        return listOf(
            "@SP",
            "AM=M-1",
            operation,
            "@SP",
            "M=M+1"
        )
    }
}