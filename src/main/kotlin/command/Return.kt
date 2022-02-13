package command

object Return : Command {
    override val validCommand: Set<String> get() = setOf("return")
    override fun getType(): CommandType = CommandType.C_RETURN
}