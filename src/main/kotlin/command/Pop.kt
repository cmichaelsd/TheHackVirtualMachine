package command

object Pop : Command {
    override val validCommand: Set<String> = setOf("pop")
    override fun getType(): CommandType = CommandType.C_POP
}