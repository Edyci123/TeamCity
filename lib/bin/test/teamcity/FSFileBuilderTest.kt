package teamcity

import org.junit.jupiter.api.assertThrows
import teamcity.builders.fsFile
import teamcity.exceptions.EmptyNameException
import kotlin.test.Test
import kotlin.test.assertEquals

class FSFileBuilderTest {

    @Test
    fun createFile() {
        val entry = fsFile {
            name = "Name"
            content = "Something"
        }
        assertEquals(entry.name, "Name")
        assertEquals(entry.content, "Something")
    }

    @Test
    fun createFileNoName() {
        assertThrows<EmptyNameException> {
            fsFile {
                name = ""
                content = "Something"
            }
        }
    }

}