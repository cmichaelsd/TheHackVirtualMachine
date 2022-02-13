package command

object Logical : Command {
    override val validCommand: Set<String> = setOf(
        "eq",
        "gt",
        "lt",
        "and",
        "or",
        "not"
    )
    override fun getType(): CommandType = CommandType.C_ARITHMETIC
}