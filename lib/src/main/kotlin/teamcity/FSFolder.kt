package teamcity

class FSFolder(name: String, val content: ArrayList<FSEntry>): FSEntry(name) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true;
        if (other !is FSFolder) return false;
        if (!super.equals(other)) return false;

        return content == other.content;
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + content.hashCode()
        return result
    }


}