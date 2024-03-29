package teamcity.builders

import teamcity.FSCreator
import teamcity.FSEntry
import teamcity.exceptions.FSEntryEmptyException
import teamcity.exceptions.PathNullException

class FSCreatorBuilder {
    private val fsCreator = FSCreator()

    var destination: String? = null
        set(value) {
            if (value == null) {
                throw PathNullException("Path must be specified")
            }
            field = value
        }
    var entry: FSEntry? = null
        set(value) {
            if (value == null) {
                throw FSEntryEmptyException("No entry provided")
            }
            field = value
        }

    fun destination(value: String) {
        destination = value
    }

    fun entry(value: FSEntry?) {
        entry = value
    }

    fun build(): FSEntry {
        if (entry == null || destination == null) {
            throw Exception("Either the entry or the destination weren't specified!")
        }
        fsCreator.create(entry!!, destination!!)
        return entry!!
    }
}

fun create(fsCreatorBuilder: FSCreatorBuilder.() -> Unit): FSEntry {
    return FSCreatorBuilder().apply(fsCreatorBuilder).build()
}