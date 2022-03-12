package command

object Call : Command {
    override val validCommand: Set<String> = setOf("call")
    override fun getType(): CommandType = CommandType.C_CALL
}