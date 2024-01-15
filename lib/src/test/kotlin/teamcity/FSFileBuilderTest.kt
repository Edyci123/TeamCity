package teamcity

import teamcity.builders.fsEntry
import teamcity.builders.fsFile
import kotlin.test.Test
import kotlin.test.assertEquals

class FSFileBuilderTest {

    @Test
    fun createEntry() {
        val entry = fsFile {
            name = "Name"
            content = "Something"
        }
        assertEquals(entry.name, "Name")
        assertEquals(entry.content, "Something")
    }
}