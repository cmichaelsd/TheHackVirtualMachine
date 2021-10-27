package parser

import java.io.File

class ParserImpl(file: File) : Parser {
    /**
     * Determines if there are more lines.
     *
     * @return Boolean
     */
    override fun hasMoreLines(): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * Reads the next command from the input and makes it the current command.
     * This routine should be called only if hasMoreLines is true.
     * Initially there is no current command.
     */
    override fun advance() {
        TODO("Not yet implemented")
    }

    /**
     * Returns a constant representing the type of the current command.
     * If the current command is an arithmetic-logical command, returns C_ARITHMETIC.
     *
     * @return Command
     */
    override fun commandType(): Command {
        TODO("Not yet implemented")
    }

    /**
     * Returns the first argument of the current command.
     * In the case of C_ARITHMETIC, the command itself (add, sub, etc) is returned.
     * Should not be called if the current command is C_RETURN.
     *
     * @return String
     */
    override fun arg1(): String {
        TODO("Not yet implemented")
    }

    /**
     * Returns the second argument of the current command.
     * Should be called only if the current command is C_PUSH, C_POP, C_FUNCTION, or C_CALL.
     *
     * @return Int
     */
    override fun arg2(): Int {
        TODO("Not yet implemented")
    }
}