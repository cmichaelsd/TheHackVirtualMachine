package command

object Push : Command {
    override val validCommand: List<String> = listOf("push")
    override fun getType(): CommandType = CommandType.C_PUSH
}