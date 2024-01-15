package teamcity

import teamcity.builders.fsFile
import teamcity.builders.fsFolder
import kotlin.test.Test
import kotlin.test.assertEquals

class FSFolderBuilderTest {
    @Test
    fun createEntry() {
        val entry = fsFolder {
            name = "folder1"
            fsFile {
                name = "MyName"
                content = "MyContent"
            }
            fsFolder {
                name = "folder2"
            }
        }
        assertEquals(2, entry.content.size)
        assertEquals("MyName", entry.content[0].name)
        assertEquals("folder2", entry.content[1].name)
        assertEquals("MyContent", (entry.content[0] as FSFile).content)
        assertEquals(entry.name, "folder1")
    }
}