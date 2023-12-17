package teamcity

import kotlin.test.Test
import kotlin.test.assertEquals

class FSFileTest {

    @Test
    fun create() {
        val name = "File";
        val content = "Content";
        val fsFile = FSFile(name, content);
        assertEquals(fsFile.content, content);
        assertEquals(fsFile.name, name);
    }

    @Test
    fun equals() {
        val name = "File";
        val content = "Content";
        val fsFile = FSFile(name, content);
        val fsFile1 = FSFile(name, content);
        assertEquals(fsFile, fsFile1);
    }
}