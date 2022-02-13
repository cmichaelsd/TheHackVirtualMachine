package codeWriter.subroutines.branching

object Label : Branch {
    override fun create(label: String): String {
        return "($label)"
    }
}