package virtualMachineTranslator

import codeWriter.CodeWriter
import command.CommandType
import parser.Parser

object VirtualMachineTranslator {
    fun initialize(parserImpl: Parser, codeWriter: CodeWriter) {
        while (parserImpl.hasMoreLines()) {
            parserImpl.advance()
            when (val commandType = parserImpl.commandType()) {
                CommandType.C_PUSH,
                CommandType.C_POP,
//                CommandType.C_FUNCTION,
//                CommandType.C_CALL
                -> {
                    codeWriter.writePushPop(commandType, parserImpl.arg1(), parserImpl.arg2())
                }
                CommandType.C_ARITHMETIC -> {
                    codeWriter.writeArithmetic(parserImpl.arg1())
                }
                else -> {}
            }
        }
        codeWriter.close()
    }
}