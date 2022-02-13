package codeWriter.subroutines.branching

interface Branch {
    fun create(label: String): String
}