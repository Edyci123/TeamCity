package teamcity

import teamcity.exceptions.CircularReferenceException
import teamcity.exceptions.UnsupportedFSEntryException
import java.io.File
import java.nio.file.FileAlreadyExistsException

class FSCreator {

    fun create(entryToCreate: FSEntry, destination: String) {
        val targetPath = File(destination, entryToCreate.name)

        when (entryToCreate) {
            is FSFile -> {
                 if (!targetPath.exists()) {
                     targetPath.parentFile.mkdirs()
                     targetPath.writeText(entryToCreate.content)
                 } else {
                     throw FileAlreadyExistsException("File already exists ${targetPath.absolutePath}")
                 }
            }

            is FSFolder -> {
                createDirectoryTree(targetPath, entryToCreate)
            }
            else -> {
                throw UnsupportedFSEntryException("Unsupported FSEntry ${entryToCreate.javaClass.name}")
            }
        }

    }

    private fun createDirectoryTree(targetPath: File, entryToCreate: FSFolder) {
        if (!targetPath.exists()) {
            targetPath.mkdirs()
        }

        for (entry in entryToCreate.content) {
            if (entryToCreate == entry) {
                throw CircularReferenceException("This will generate a circular reference.")
            }
            create(entry, targetPath.absolutePath)
        }
    }

}