package codeWriter.subroutines.stack

interface Stack {
    fun create(segment: String, index: Int): List<String>
}