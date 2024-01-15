package teamcity.builders

import teamcity.FSEntry
import teamcity.FSEntryImpl
import teamcity.exceptions.EmptyNameException

open class FSEntryBuilder() {
    protected var fsEntry = FSEntryImpl()
    private var nameCalled = 0

    var name: String
        get() {
            return fsEntry.name
        }
        set(value) {
            nameCalled = 1
            if (value == "") {
                throw EmptyNameException("Every entry must have a name")
            }
            fsEntry.name = value
        }

    fun name(value: String) {
        name = value
    }

    protected fun checkName() {
        if (nameCalled == 0) {
            throw EmptyNameException("Every entry must have a name")
        }
    }

    open fun build(): FSEntry {
        checkName()
        return fsEntry
    }
}

fun fsEntry(fsEntryBuilder: FSEntryBuilder.() -> Unit): FSEntry {
    return FSEntryBuilder().apply(fsEntryBuilder).build()
}