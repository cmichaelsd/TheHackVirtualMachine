package codeWriter.subroutines.arithmeticLogical

interface Operation {
    fun create(operation: String): List<String>
}