package teamcity

import teamcity.builders.create
import teamcity.builders.fsFile
import teamcity.builders.fsFolder
import java.io.File
import java.nio.file.Paths
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FSCreatorBuilderTest {

    private val path = Paths.get("").toAbsolutePath().toString()

    @Test
    fun testCreateFolderWithFile() {
        val fsEntry = create {
            destination = path
            fsEntry = fsFolder {
                name("Folder1")
                fsFile {
                    name("File1")
                    content("This is file1")
                }
            }
        }

        assertEquals("Folder1", fsEntry.name)
        assertEquals("File1", (fsEntry as FSFolder).content[0].name)
        assertEquals("This is file1", (fsEntry.content[0] as FSFile).content)

        val folder = File(Paths.get(path, fsEntry.name).toString())
        assertTrue(folder.deleteRecursively())
    }

    @Test
    fun testCreateFile() {
        val fsEntry = create {
            destination = path
            fsEntry = fsFile {
                name = "Name"
            }
        }
        assertEquals("Name", fsEntry.name)
        val file = File(Paths.get(path, fsEntry.name).toString())
        assertTrue(file.delete())
    }
}