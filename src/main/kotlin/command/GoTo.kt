package command

object GoTo : Command {
    override val validCommand: Set<String> = setOf("goto")
    override fun getType(): CommandType = CommandType.C_GOTO
}
