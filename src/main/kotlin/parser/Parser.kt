package parser

interface Parser {
    fun hasMoreLines(): Boolean
    fun advance()
    fun commandType(): Command
    fun arg1(): String
    fun arg2(): Int
}