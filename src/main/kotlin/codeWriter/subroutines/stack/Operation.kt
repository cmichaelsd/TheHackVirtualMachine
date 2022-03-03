package codeWriter.subroutines.stack

interface Operation {
    fun create(segment: String, index: Int): String
}