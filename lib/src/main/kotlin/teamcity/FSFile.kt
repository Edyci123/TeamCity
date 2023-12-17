package teamcity

class FSFile(name: String, val content: String) : FSEntry(name) {

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