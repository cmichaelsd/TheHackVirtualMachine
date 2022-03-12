package command

object If : Command {
    override val validCommand: Set<String> = setOf("if-goto")
    override fun getType(): CommandType = CommandType.C_IF
}