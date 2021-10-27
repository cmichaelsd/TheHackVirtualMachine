package codeWriter

import parser.Command

interface CodeWriter {
    fun writeArithmetic(command: String)
    fun writePushPop(command: Command, segment: String, index: Int)
    fun close()
}