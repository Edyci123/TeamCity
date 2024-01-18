package teamcity

import org.junit.jupiter.api.assertThrows
import teamcity.builders.fsEntry
import teamcity.exceptions.EmptyNameException
import kotlin.test.Test
import kotlin.test.assertEquals

class FSEntryBuilderTest {

    @Test
    fun createEntry() {
        val entry = fsEntry {
            name("Name")
        }
        assertEquals(entry.name, "Name")
    }

    @Test
    fun createEntryNotSpecifyName() {
        assertThrows<EmptyNameException> {
            fsEntry {
            }
        }
    }

}