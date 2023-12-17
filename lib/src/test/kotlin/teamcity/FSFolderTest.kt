package teamcity

import kotlin.test.Test
import kotlin.test.assertEquals

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