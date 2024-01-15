package teamcity.builders

import teamcity.FSEntry
import teamcity.FSFolder

class FSFolderBuilder : FSEntryBuilder() {
    init {
        fsEntry = FSFolder("1")
    }

    private var fsFolder: FSFolder
        get() {
            return fsEntry as FSFolder
        }
        set(value) {
            fsEntry = value
        }

    fun addContent(fsEntryBuilder: FSEntryBuilder): FSEntryBuilder {
        val newEntry = fsEntryBuilder.build()
        fsFolder.content.add(newEntry)
        return this
    }

    override fun build(): FSFolder {
        return fsFolder
    }
}

fun FSFolderBuilder.fsEntry(fsEntryBuilder: FSEntryBuilder.() -> Unit) : FSEntryBuilder {
    val newEntry = FSEntryBuilder().apply(fsEntryBuilder)
    return addContent(newEntry)
}

fun FSFolderBuilder.fsFile(fsFileBuilder: FSFileBuilder.() -> Unit) : FSEntryBuilder {
    val newEntry = FSFileBuilder().apply(fsFileBuilder)
    return addContent(newEntry)
}

fun FSFolderBuilder.fsFolder(fsFolderBuilder: FSFolderBuilder.() -> Unit) : FSEntryBuilder {
    val newEntry = FSFolderBuilder().apply(fsFolderBuilder)
    return addContent(newEntry)
}

fun fsFolder(fsFolderBuilder : FSFolderBuilder.() -> Unit): FSFolder {
    return FSFolderBuilder().apply(fsFolderBuilder).build()
}