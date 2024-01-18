package teamcity.builders

import teamcity.FSEntry
import teamcity.FSEntryImpl
import teamcity.exceptions.EmptyNameException

open class FSEntryBuilder {
    protected var fsEntry = FSEntryImpl()
    private var isNameSet = false

    var name: String
        get() = fsEntry.name
        set(value) {
            if (value == "") {
                throw EmptyNameException("Every entry must have a name")
            }
            fsEntry.name = value
            isNameSet = true
        }

    fun name(value: String) {
        name = value
    }

    protected fun checkName() {
        if (isNameSet) throw EmptyNameException("Every entry must have a name")
    }

    open fun build(): FSEntry {
        checkName()
        return fsEntry
    }
}

fun fsEntry(fsEntryBuilder: FSEntryBuilder.() -> Unit): FSEntry {
    return FSEntryBuilder().apply(fsEntryBuilder).build()
}