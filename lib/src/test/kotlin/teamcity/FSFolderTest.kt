package teamcity

import org.junit.jupiter.api.assertThrows
import teamcity.exceptions.SameNameException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class FSFolderTest { 

    @Test
    fun create() {
        val files = ArrayList<FSEntry>();
        for (i in 0..4) {
            files.add(FSFile("file$i", "This is file $i"))
        }
        val fsFolder = FSFolder("folder", files);
        assertEquals(files, fsFolder.content);
        assertEquals("folder", fsFolder.name);
        assertNotNull(fsFolder.uuid)
    }

    @Test
    fun createFolderNoContent() {
        val name = "Folder"
        val fsFolder = FSFolder(name)
        assertEquals(fsFolder.name, name)
        assertTrue(fsFolder.content.isEmpty())
        assertNotNull(fsFolder.uuid)
    }

    @Test
    fun createIdenticalNamesFiles() {
        val files = ArrayList<FSEntry>();
        for (i in 0..1) {
            files.add(FSFile("file", "This is file $i"))
        }
        assertThrows<SameNameException> {
            FSFolder("folder", files);
        }
    }

    @Test
    fun createIdenticalNamesFolders() {
        val files = ArrayList<FSEntry>();
        for (i in 0..1) {
            files.add(FSFolder("folder"))
        }
        assertThrows<SameNameException> {
            FSFolder("folder", files);
        }
    }

    @Test
    fun equals() {
        val files = ArrayList<FSEntry>();
        for (i in 0..4) {
            files.add(FSFile("file$i", "This is file $i"))
        }
        val fsFolder = FSFolder("folder", files);
        val fsFolder1 = FSFolder("folder", files);

        assertEquals(fsFolder, fsFolder1)
    }


}