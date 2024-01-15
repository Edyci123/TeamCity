package teamcity.builders

import teamcity.FSEntry
import teamcity.FSFile
import teamcity.FSFolder

class FSFileBuilder : FSEntryBuilder() {
    init {
        fsEntry = FSFile("PlaceholderName")
    }

    private var fsFile: FSFile
        get() {
            return fsEntry as FSFile
        }
        set(value) {
            fsEntry = value
        }

    var content: String
        get() {
            return fsFile.content
        }
        set(value) {
            fsFile.content = value
        }

    override fun build(): FSFile {
        checkName()
        return fsFile
    }
}

fun fsFile(fsFileBuilder : FSFileBuilder.() -> Unit): FSFile {
    return FSFileBuilder().apply(fsFileBuilder).build()
}