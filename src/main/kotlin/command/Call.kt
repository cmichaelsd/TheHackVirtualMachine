package command

object Call : Command {
    override val validCommand: Set<String> get() = setOf("call")
    override fun getType(): CommandType = CommandType.C_CALL
}