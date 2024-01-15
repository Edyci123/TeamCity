package teamcity.builders

import teamcity.FSEntry
import teamcity.FSEntryImpl

open class FSEntryBuilder() {
    protected var fsEntry = FSEntryImpl()

    var name: String
        get() {
            return fsEntry.name
        }
        set(value) {
            fsEntry.name = value
        }

    open fun build(): FSEntry {
        return fsEntry
    }
}

fun fsEntry(fsEntryBuilder : FSEntryBuilder.() -> Unit): FSEntry {
    return FSEntryBuilder().apply(fsEntryBuilder).build()
}