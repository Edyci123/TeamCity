package teamcity

import teamcity.exceptions.CircularReferenceException
import teamcity.exceptions.NoPermissionsException
import teamcity.exceptions.UnsupportedFSEntryException
import java.io.File
import java.nio.file.FileAlreadyExistsException
import java.nio.file.Files
import java.util.*

class FSCreator {

    private val uuidMap: MutableMap<UUID, Boolean> = mutableMapOf()

    fun create(entryToCreate: FSEntry, destination: String) {

        if  (entryToCreate is FSFolder) {
            if (uuidMap.contains(entryToCreate.uuid)) {
                throw CircularReferenceException("This will generate a circular reference.")
            } else {
                uuidMap[entryToCreate.uuid] = true
            }
        }

        val targetPath = File(destination, entryToCreate.name)

        if (!Files.isWritable(targetPath.parentFile.toPath().toAbsolutePath())) {
            throw NoPermissionsException("You don't have enough permissions to create a file here!")
        }

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
                uuidMap.remove(entryToCreate.uuid)
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
            create(entry, targetPath.absolutePath)
        }
    }

}