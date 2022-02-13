package command

object GoTo : Command {
    override val validCommand: Set<String> get() = setOf("goto")
    override fun getType(): CommandType = CommandType.C_GOTO
}
