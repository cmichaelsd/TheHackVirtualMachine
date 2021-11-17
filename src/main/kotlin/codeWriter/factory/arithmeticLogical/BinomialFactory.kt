package codeWriter.factory.arithmeticLogical

interface BinomialFactory {
    fun create(operation: String): List<String>
}