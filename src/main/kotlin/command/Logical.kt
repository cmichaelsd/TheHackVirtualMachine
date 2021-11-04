package command

object Logical : Command {
    override val validCommand: List<String> = listOf(
        "eg",
        "gt",
        "lt",
        "and",
        "or",
        "not"
    )
    override fun getType(): CommandType = CommandType.C_ARITHMETIC
}