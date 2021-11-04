package parser

import command.CommandType

interface Parser {
    fun hasMoreLines(): Boolean
    fun advance()
    fun commandType(): CommandType
    fun arg1(): String
    fun arg2(): Int
}