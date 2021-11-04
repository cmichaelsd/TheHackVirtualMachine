package parser

import command.*
import segment.Segment
import segment.SegmentException
import java.io.File

class ParserImpl(file: File) : Parser {

    private var vmInstructions: List<String> = file.useLines { it.toList() }

    /**
     * Total number of lines in the file to parse.
     */
    private var totalLines = vmInstructions.size - 1

    /**
     * The current line being parsed, will increase with each call to #advance().
     */
    var currentLine = -1

    /**
     * The line number of significant lines parsed, will increase with each call to #advance().
     */
    var significantLine = -1

    /**
     * The current instruction being parsed, is set in #advance().
     */
    var currentInstruction: List<String> = listOf()

    /**
     * Determines if there are more lines.
     *
     * @return Boolean
     */
    override fun hasMoreLines(): Boolean = currentLine < totalLines

    /**
     * Reads the next command from the input and makes it the current command.
     * This routine should be called only if hasMoreLines is true.
     * Initially there is no current command.
     */
    override fun advance() {
        ++currentLine

        while (vmInstructions[currentLine].isBlank() || vmInstructions[currentLine].indexOf("//") == 0) {
            if (hasMoreLines()) ++currentLine
            else return
        }

        val lineBeingRead = vmInstructions[currentLine]

        val commentIndex: Int = lineBeingRead.indexOf("//")

        var instruction = ""

        for ((index, character) in lineBeingRead.withIndex()) {
            if (index == commentIndex) break
            instruction += character
        }

        currentInstruction = instruction
            .replace("\\s+", " ")
            .split(" ")
    }

    /**
     * Returns a constant representing the type of the current command.
     * If the current command is an arithmetic-logical command, returns C_ARITHMETIC.
     *
     * @return Command
     * @throws CommandTypeException
     */
    @Throws(CommandTypeException::class)
    override fun commandType(): CommandType {
        val command = currentInstruction[0]
        if (Push.has(command)) return Push.getType()
        if (Pop.has(command)) return Pop.getType()
        if (Arithmetic.has(command)) return Arithmetic.getType()
        if (Logical.has(command)) return Logical.getType()
        throw CommandTypeException("Illegal command type token at line $currentLine")
    }

    /**
     * Returns the first argument of the current command.
     * In the case of C_ARITHMETIC, the command itself (add, sub, etc) is returned.
     * Should not be called if the current command is C_RETURN.
     *
     * @return String
     * @throws SegmentException
     */
    @Throws(SegmentException::class)
    override fun arg1(): String {
        if (commandType() == CommandType.C_ARITHMETIC) return currentInstruction[0]
        if (Segment.has(currentInstruction[1])) return currentInstruction[1]
        throw SegmentException("Illegal argument token at line $currentLine")
    }

    /**
     * Returns the second argument of the current command.
     * Should be called only if the current command is C_PUSH, C_POP, C_FUNCTION, or C_CALL.
     *
     * @return Int
     * @throws ConstantValueException
     */
    @Throws(ConstantValueException::class)
    override fun arg2(): Int {
        val argumentTwo = currentInstruction[2].toInt()
        if (arg1() == "constant" && argumentTwo in 0..32767) return argumentTwo
        if (arg1() == "constant" && argumentTwo !in 0..32767) throw ConstantValueException("Illegal value at line $currentLine")
        return argumentTwo
    }
}