package teamcity

abstract class FSEntry {
    var name: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FSEntry) return false

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}