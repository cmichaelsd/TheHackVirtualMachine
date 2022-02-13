package command

object Arithmetic : Command {
    override val validCommand: Set<String> = setOf(
        "add",
        "sub",
        "neg"
    )
    override fun getType(): CommandType = CommandType.C_ARITHMETIC
}