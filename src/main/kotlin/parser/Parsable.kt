package parser

import command.Push

interface Parsable {
    /**
     * A list containing all valid commands for the current command type.
     */
    val validCommand: Set<String>

    /**
     * Compares a given command against valid commands for this command type.
     *
     * @param  command a string representing a command
     * @return Boolean
     */
    fun has(command: String): Boolean = validCommand.contains(command)
}