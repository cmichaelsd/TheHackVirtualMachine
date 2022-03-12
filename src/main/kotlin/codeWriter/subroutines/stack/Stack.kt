package codeWriter.subroutines.stack

interface Stack {
    fun incrementStackPointer(stringBuilder: StringBuilder)
    fun decrementStackPointer(stringBuilder: StringBuilder)
    fun push(fileName: String, segment: String, index: Int): String
    fun pop(fileName: String, segment: String, index: Int): String
}