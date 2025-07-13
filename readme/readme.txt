# DirectoryList Serialization

## Compile:
javac DirectoryList.java

## Run:
java DirectoryList relative/path

## What the program does

1. Lists all files and folders inside the given directory and its subdirectories, showing type ([D] for directory, [F] for file) and last modification date.

2. Saves the listing as a serialized object in the file `listing.ser`.

3. Reads back the serialized file `listing.ser` and prints its content to the console.


Make sure you run the commands from the directory where `DirectoryList.java` is located and where you want to create/read the `listing.ser` file.

