package teamcity

import org.junit.jupiter.api.assertThrows
import teamcity.exceptions.CircularReferenceException
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.FileAlreadyExistsException
import java.nio.file.Paths
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FSCreatorTest {

    private val fsCreator: FSCreator = FSCreator()
    private val path = Paths.get("").toAbsolutePath().toString()

    @Test
    fun createNewFile() {
        val fsFile = FSFile("File1", "This is my content!")
        fsCreator.create(fsFile, path)
        val file = File(Paths.get(path, fsFile.name).toString())
        assertTrue(file.exists())
        assertEquals(file.readLines()[0], fsFile.content)
        assertTrue(file.delete())
    }

    @Test
    fun createFileWithSpecialChars() {
        val fsFile = FSFile(".\"\'\\; §`~!Fi le", "Content")
        fsCreator.create(fsFile, path)
        val file = File(Paths.get(path, fsFile.name).toString())
        assertTrue(file.exists())
        assertEquals(file.name, fsFile.name)
        assertTrue(file.delete())
    }

    @Test
    fun createFileWithNameTooLong() {
        var name = ""
        for (i in 1..1000) {
            name += "f"
        }
        val fsFile = FSFile(name, "")
        assertThrows<FileNotFoundException> {
            fsCreator.create(fsFile, path)
        }
    }

    @Test
    fun createNewFileNoContent() {
        val fsFile = FSFile("File", "")
        fsCreator.create(fsFile, path)
        val file = File(Paths.get(path, fsFile.name).toString())
        assertTrue(file.exists())
        assertEquals(file.readLines().size, 0)
        assertTrue(File(Paths.get(path, fsFile.name).toString()).delete())
    }

    @Test
    fun throwErrorFileAlreadyExists() {
        val fsFile = FSFile("File1", "This is the file")
        fsCreator.create(fsFile, path)
        val fsFile1 = FSFile("File1", "This is the file")
        assertThrows<FileAlreadyExistsException> {
            fsCreator.create(fsFile1, path)
        }
        assertTrue(File(Paths.get(path, fsFile.name).toString()).delete())
    }

    @Test
    fun createFileInNonExistentFolder() {
        val fsFile = FSFile("fileName", "MyFile")
        val newPath = Paths.get(path, "temp").toString()
        fsCreator.create(fsFile, newPath)
        val newFile = File(newPath, fsFile.name)
        assertTrue(newFile.exists())
        assertTrue(newFile.delete())
    }

    @Test
    fun createEmptyFolder() {
        val fsFolder = FSFolder("folderName", ArrayList())
        fsCreator.create(fsFolder, path)
        val folder = File(Paths.get(path, fsFolder.name).toString())
        assertTrue(folder.exists())
        assertTrue(folder.deleteRecursively())
    }

    @Test
    fun createFolderWithContent() {
        val files = ArrayList<FSEntry>()
        for (i in 0..4) {
            files.add(FSFile("file$i", "This is file $i"))
        }
        val fsFolder = FSFolder("folder", files)

        fsCreator.create(fsFolder, path)

        val folder = File(Paths.get(path, fsFolder.name).toString())

        assertTrue(folder.exists())
        for (i in 0..4) {
            assertEquals(File(Paths.get(path, fsFolder.name, files[i].name).toString()).readLines()[0], "This is file $i")
        }
        assertTrue(folder.deleteRecursively())
    }

    @Test
    fun createNestedFoldersWithContent() {
        val files = ArrayList<FSEntry>()
        for (i in 0..4) {
            files.add(FSFile("file$i", "This is file $i"))
        }
        val fsFolder = FSFolder("folder", files)
        val entries = ArrayList<FSEntry>(files)
        entries.add(fsFolder)
        val fsFolderRoot = FSFolder("folderRoot", entries)
        fsCreator.create(fsFolderRoot, path)

        val folderRoot = File(Paths.get(path, fsFolderRoot.name).toString())
        val folder = File(Paths.get(path, fsFolderRoot.name, fsFolder.name).toString())

        assertTrue(folderRoot.exists())
        assertTrue(folder.exists())
        for (i in 0..4) {
            val path0 = Paths.get(path, fsFolderRoot.name).toString()
            val path1 = Paths.get(path, fsFolderRoot.name, fsFolder.name).toString()
            assertTrue(File(Paths.get(path0, entries[i].name).toString()).exists())
            assertTrue(File(Paths.get(path1, entries[i].name).toString()).exists())
        }

        assertTrue(folderRoot.deleteRecursively())
    }

    @Test
    fun createCircularReferenceStructure() {
        val files = ArrayList<FSEntry>()
        for (i in 0..4) {
            files.add(FSFile("file$i", "This is file $i"))
        }
        val fsFolder = FSFolder("folder", files)
        files.add(fsFolder)
        val fsFolderRoot = FSFolder("folderRoot", files)
        assertThrows<CircularReferenceException> {
            fsCreator.create(fsFolderRoot, path)
        }
        val folderRoot = File(Paths.get(path, fsFolderRoot.name).toString())
        assertTrue(folderRoot.deleteRecursively());
    }

}