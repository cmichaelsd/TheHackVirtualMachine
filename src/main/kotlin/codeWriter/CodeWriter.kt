package codeWriter

import command.CommandType

interface CodeWriter {
    fun writeArithmetic(command: String)
    fun writePushPop(commandType: CommandType, segment: String, index: Int)
    fun close()
}