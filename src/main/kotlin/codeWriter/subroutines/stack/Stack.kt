package codeWriter.subroutines.stack

interface Stack {
    fun incrementStackPointer(stringBuilder: StringBuilder)
    fun decrementStackPointer(stringBuilder: StringBuilder)
}