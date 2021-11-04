package command

import parser.Parsable

interface Command : Parsable {
    /**
     * Returns the command type for the current object.
     */
    fun getType(): CommandType
}