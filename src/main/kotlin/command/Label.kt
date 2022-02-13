package command

object Label : Command {
    override val validCommand: Set<String> get() = setOf("label")
    override fun getType(): CommandType = CommandType.C_LABEL
}