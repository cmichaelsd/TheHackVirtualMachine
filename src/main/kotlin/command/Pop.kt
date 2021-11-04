package command

object Pop : Command {
    override val validCommand: List<String> = listOf("pop")
    override fun getType(): CommandType = CommandType.C_POP
}