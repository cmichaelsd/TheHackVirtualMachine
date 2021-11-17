package codeWriter.factory.arithmeticLogical

class BinomialFactoryImpl: BinomialFactory {
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