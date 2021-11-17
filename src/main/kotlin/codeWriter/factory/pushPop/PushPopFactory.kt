package codeWriter.factory.pushPop

interface PushPopFactory {
    fun create(segment: String, index: Int): List<String>
}