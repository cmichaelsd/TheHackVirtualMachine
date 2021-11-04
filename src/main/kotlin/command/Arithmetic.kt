package command

object Arithmetic : Command {
    override val validCommand: List<String> = listOf(
        "add",
        "sub",
        "neg"
    )
    override fun getType(): CommandType = CommandType.C_ARITHMETIC
}