# TeamCity

This is an app to create a file structure.

FSFile has 2 constructors `(name: String, content: String)` and `(name: String)`. It throws an
error on an empty name in the init function.

FSFolder has 2 constructors `(name: String, content: String)` and `(name: String)`.
In the init function we test if any of the files or folders
in the same folder have the same name, if true, we throw an error,
also if it gets an empty name we throw an error.

FSCreator has 2 functions:
+ `create(entryToCreate: FSEntry, destination(the absolute path): String)` - here we create a File object 
with the desired `destination`, and name, then we check if 
we have enough permissions to write anything in the parent folder. 
Afterward we test if the `entryToCreate` is a `FSFile` or `FSFolder`. In the FSFile case,
we check if it already exists, if it does we throw an error, else we create the 
directories for the parent to lead to that path, if they don't already exist,
and then we create the file and write the content. If it is a FSFolder
we call the function `createDirectoryTree(targetPath, entryToCreate)`
+ `createDirectoryTree(targetPath: File, entryToCreate: FSFolder)` - we will check again if the `targetPath`
exists, and if not create the directories leading to that path. Check if any of the entries in the content
of the `entryToCreate` is equal to its parent, if it is throw an error. Afterward call the create method
with the entry in the content and the absolute path to the target.

It is built in kotlin, so you require to have that on your computer.

There is a test class for every class used.
After any of the tests, all the files created will be removed.

FSFile:
+ Create a FSFile without content
+ Create a FSFile with content
+ Create a FSFile with no name throws an error
+ Equals function

FSFolder:
+ Create a FSFolder without content
+ Create a FSFolder with some files as content
+ Create a FSFolder with files that have the same name
+ Create a FSFolder with folders that have the same name
+ Equals function


FSCreate:
+ Create a file with some content
+ Create a file with special chars inside its name
+ Create a file with a name that is too long throws error
+ Create a file with no content
+ Create a file that already exists throws error
+ Create a file in a nonexistent folder
+ Create an empty folder
+ Create a folder with some files inside
+ Create a nested folders structure with files inside them
+ Create a circular reference structure throws an error
    

