package teamcity

import teamcity.builders.fsEntry
import kotlin.test.Test
import kotlin.test.assertEquals

class FSEntryBuilderTest {

    @Test
    fun createEntry() {
        val entry = fsEntry {
            name = "Name"
        }
        assertEquals(entry.name, "Name")
    }
}