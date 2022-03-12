package command

object Function : Command {
    override val validCommand: Set<String> = setOf("function")
    override fun getType(): CommandType = CommandType.C_FUNCTION
}