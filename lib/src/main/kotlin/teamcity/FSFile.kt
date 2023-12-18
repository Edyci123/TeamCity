package teamcity

import teamcity.exceptions.EmptyNameException

class FSFile(name: String, val content: String) : FSEntry(name) {

    constructor(name: String) : this(name, "")

    init {
        if (name == "") {
            throw EmptyNameException("No name given to the file.")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FSFile) return false
        if (!super.equals(other)) return false

        return content == other.content
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + content.hashCode()
        return result
    }

}