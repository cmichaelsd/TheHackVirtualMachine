package command

object Push : Command {
    override val validCommand: Set<String> = setOf("push")
    override fun getType(): CommandType = CommandType.C_PUSH
}