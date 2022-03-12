package command

object Return : Command {
    override val validCommand: Set<String> = setOf("return")
    override fun getType(): CommandType = CommandType.C_RETURN
}