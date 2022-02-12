package codeWriter

import command.CommandType

interface CodeWriter {
    fun writeArithmetic(command: String)
    fun setFileName(fileName: String)
    fun writePushPop(commandType: CommandType, segment: String, index: Int)
    fun writeLabel(label: String)
    fun writeGoTo(label: String)
    fun writeIf(label: String)
    fun writeFunction(functionName: String, nVars: Int)
    fun writeCall(functionName: String, nArgs: Int)
    fun writeReturn()
    fun close()
}