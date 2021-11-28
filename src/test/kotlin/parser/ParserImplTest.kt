package parser

import command.CommandType
import org.junit.Before
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals


internal class ParserImplTest {

    private val expectedResult = listOf(
        "push constant 10",
        "pop local 0",
        "push constant 21",
        "push constant 22",
        "pop argument 2",
        "pop argument 1",
        "push constant 36",
        "pop this 6",
        "push constant 42",
        "push constant 45",
        "pop that 5",
        "pop that 2",
        "push constant 510",
        "pop temp 6",
        "push local 0",
        "push that 5",
        "add",
        "push argument 1",
        "sub",
        "push this 6",
        "push this 6",
        "add",
        "sub",
        "push temp 6",
        "add"
    )

    /**
     * A test instantiation of ParserImpl class.
     */
    private lateinit var testParserImpl: ParserImpl

    @Before
    fun setup() {
        val path = this.javaClass.getResource("/BasicTest.vm")?.path ?: ""
        val file = File(path)
        testParserImpl = ParserImpl(file)
    }

    @Test
    fun hasMoreLines() {
        val isMoreLines = arrayListOf<Boolean>()
        for (i in 0..24) isMoreLines.add(true)
        isMoreLines.add(false)

        var testIndex = 0
        while(testParserImpl.hasMoreLines()) {
            assertEquals(testParserImpl.hasMoreLines(), isMoreLines[testIndex++])
            testParserImpl.advance()
        }
    }

    @Test
    fun advance() {
        for (result in expectedResult) {
            testParserImpl.advance()
            assertEquals(result, testParserImpl.currentInstruction.joinToString(" "))
        }
    }

    @Test
    fun commandType() {
        val commands = listOf(
            Pair("push", CommandType.C_PUSH),
            Pair("pop", CommandType.C_POP),
            Pair("add", CommandType.C_ARITHMETIC),
            Pair("sub", CommandType.C_ARITHMETIC),
            Pair("neg", CommandType.C_ARITHMETIC),
            Pair("eg", CommandType.C_ARITHMETIC),
            Pair("gt", CommandType.C_ARITHMETIC),
            Pair("lt", CommandType.C_ARITHMETIC),
            Pair("and", CommandType.C_ARITHMETIC),
            Pair("or", CommandType.C_ARITHMETIC),
            Pair("not", CommandType.C_ARITHMETIC)
        )
        for (pair in commands) {
            testParserImpl.currentInstruction = listOf(pair.first)
            assertEquals(pair.second, testParserImpl.commandType())
        }
    }

    @Test
    fun arg1() {
        val arg1List = listOf(
            "constant",
            "local",
            "constant",
            "constant",
            "argument",
            "argument",
            "constant",
            "this",
            "constant",
            "constant",
            "that",
            "that",
            "constant",
            "temp",
            "local",
            "that",
            "add",
            "argument",
            "sub",
            "this",
            "this",
            "add",
            "sub",
            "temp",
            "add"
        )

        var testIndex = 0
        while(testParserImpl.hasMoreLines()) {
            testParserImpl.advance()
            if (testParserImpl.commandType() != CommandType.C_RETURN) {
                assertEquals(testParserImpl.arg1(), arg1List[testIndex++])
            }
        }
    }

    @Test
    fun arg2() {
        val arg2List = listOf(
            10,
            0,
            21,
            22,
            2,
            1,
            36,
            6,
            42,
            45,
            5,
            2,
            510,
            6,
            0,
            5,
            1,
            6,
            6,
            6
        )

        var testIndex = 0
        while(testParserImpl.hasMoreLines()) {
            testParserImpl.advance()
            if (
                testParserImpl.commandType() == CommandType.C_PUSH ||
                testParserImpl.commandType() == CommandType.C_POP ||
                testParserImpl.commandType() == CommandType.C_FUNCTION ||
                testParserImpl.commandType() == CommandType.C_CALL
            ) {
                assertEquals(testParserImpl.arg2(), arg2List[testIndex++])
            }
        }
    }
}