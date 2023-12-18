package teamcity

import teamcity.exceptions.CircularReferenceException
import teamcity.exceptions.EmptyNameException
import teamcity.exceptions.SameNameException

class FSFolder(name: String, val content: ArrayList<FSEntry>): FSEntry(name) {

    constructor(name: String) : this(name, ArrayList())

    init {
        if (name == "") {
            throw EmptyNameException("No name given to the file.")
        }

        val namesFilesMap: MutableMap<String, Boolean> = mutableMapOf()
        val namesFoldersMap: MutableMap<String, Boolean> = mutableMapOf();

        for (entry in content) {
            if (this == entry) {
                throw CircularReferenceException("A folder can't contain itself.")
            }

            if (entry is FSFile) {
                if (namesFilesMap.containsKey(entry.name)) {
                    throw SameNameException("Two files can't have the same name: ${entry.name}.");
                }
                namesFilesMap[entry.name] = true
            } else {
                if (namesFoldersMap.containsKey(entry.name)) {
                    throw SameNameException("Two folders can't have the same name: ${entry.name}.");
                }
                namesFoldersMap[entry.name] = true
            }
        }
    }

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