package teamcity

import org.junit.jupiter.api.assertThrows
import teamcity.exceptions.EmptyNameException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FSFileTest {

    @Test
    fun create() {
        val name = "File"
        val content = "Content"
        val fsFile = FSFile(name, content)
        assertEquals(fsFile.content, content)
        assertEquals(fsFile.name, name)
    }

    @Test
    fun createFileNoContent() {
        val name = "File"
        val fsFile = FSFile(name)
        assertEquals(fsFile.name, name)
        assertTrue(fsFile.content.isEmpty())
    }

    @Test
    fun createEmptyName() {
        val name = ""
        val content = "Content"
        assertThrows<EmptyNameException> {
            FSFile(name, content)
        }
    }

    @Test
    fun equals() {
        val name = "File"
        val content = "Content"
        val fsFile = FSFile(name, content)
        val fsFile1 = FSFile(name, content)
        assertEquals(fsFile, fsFile1)
    }
}
