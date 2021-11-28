package codeWriter

import codeWriter.subroutines.arithmeticLogical.BinomialImpl
import codeWriter.subroutines.stack.PopImpl
import codeWriter.subroutines.stack.PushImpl
import org.junit.Assert.assertArrayEquals
import segment.Segment
import kotlin.test.Test

internal class CodeWriterImplTest {

    @Test
    fun writeArithmetic() {
        val arithmeticList = listOf(
            "D=D+M",
            "D=D-M",
            "D=D&M",
            "D=D|M"
        )

        for (binomial in arithmeticList) {
            val expected = arrayOf(
                "@SP",
                "AM=M-1",
                "D=M",
                "@SP",
                "AM=M-1",
                binomial,
                "@SP",
                "M=M+1"
            )

            val actual = BinomialImpl().create(binomial).toTypedArray()
            assertArrayEquals(expected, actual)
        }
    }

    @Test
    fun writePushPop() {
        pushAddressing()
        pushConstant()
        pushContext()
        popAddressing()
        popContext()
    }

    private fun popContext() {
        val pairs = listOf(
            Pair("local", 0),
            Pair("argument", 2),
            Pair("this", 2),
            Pair("that", 6)
        )

        for (pair in pairs) {
            val actual = PopImpl(FILE_NAME).create(pair.first, pair.second).toTypedArray()
            val expected = setAddressToContext(pair.first, pair.second).toMutableList()
            expected.addAll(listOf("D=D+A", "@${Segment.getTranslation(pair.first)}", "M=D"))
            expected.addAll(listOf("@SP", "M=M-1", "AD=M"))
            expected.addAll(listOf("@${Segment.getTranslation(pair.first)}", "A=M", "M=D"))
            expected.addAll(setAddressToContext(pair.first, pair.second))
            expected.addAll(listOf("D=A-D", "@${Segment.getTranslation(pair.first)}", "M=D"))
            assertArrayEquals(expected.toTypedArray(), actual)
        }
    }

    private fun popAddressing() {
        val pairs = listOf(
            Pair("pointer", 0),
            Pair("pointer", 1),
            Pair("static", 8),
            Pair("temp", 6),
        )

        for (pair in pairs) {
            val actual = PopImpl(FILE_NAME).create(pair.first, pair.second).toTypedArray()
            assertArrayEquals(
                arrayOf(
                    "@SP",
                    "M=M-1",
                    "AD=M",
                    "@${getAddress(pair.first, pair.second)}",
                    "M=D",
                ),
                actual
            )
        }
    }

    private fun pushContext() {
        val pairs = listOf(
            Pair("local", 0),
            Pair("argument", 2),
            Pair("this", 2),
            Pair("that", 6)
        )

        for (pair in pairs) {
            val actual = PushImpl(FILE_NAME).create(pair.first, pair.second).toTypedArray()
            val expected = setAddressToContext(pair.first, pair.second).toMutableList()
            expected.addAll(listOf(
                "D=D+A",
                "A=D",
                "D=M",
                "@SP",
                "A=M",
                "M=D",
                "@SP",
                "M=M+1"
            ))
            assertArrayEquals(expected.toTypedArray(), actual)
        }
    }

    private fun pushConstant() {
        val pairs = mutableListOf<Pair<String, Int>>()
        for (i in  0..32767) pairs.add(Pair("constant", i))

        for (pair in pairs) {
            val actual = PushImpl(FILE_NAME).create(pair.first, pair.second).toTypedArray()
            assertArrayEquals(
                arrayOf(
                    "@${pair.second}",
                    "D=A",
                    "@SP",
                    "A=M",
                    "M=D",
                    "@SP",
                    "M=M+1"
                ),
                actual
            )
        }
    }

    private fun pushAddressing() {
        val pairs = listOf(
            Pair("pointer", 0),
            Pair("pointer", 1),
            Pair("static", 8),
            Pair("temp", 6),
        )

        for (pair in pairs) {
            val actual = PushImpl(FILE_NAME).create(pair.first, pair.second).toTypedArray()
            assertArrayEquals(
                arrayOf(
                    "@${getAddress(pair.first, pair.second)}",
                    "D=M",
                    "@SP",
                    "A=M",
                    "M=D",
                    "@SP",
                    "M=M+1"
                ),
                actual
            )
        }
    }

    private fun getAddress(segment: String, index: Int): String {
        return when(segment) {
            "pointer" -> if (index == 0) "THIS" else "THAT"
            "static" -> "$FILE_NAME.$index"
            "temp" -> "${5 + index}"
            else -> ""
        }
    }

    private fun setAddressToContext(segment: String, index: Int): List<String> {
        return listOf(
            "@$index",
            "D=A",
            "@${Segment.getTranslation(segment)}",
            "A=M"
        )
    }

    companion object {
        const val FILE_NAME = "Test"
    }
}