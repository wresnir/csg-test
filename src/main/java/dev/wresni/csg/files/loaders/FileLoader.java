package dev.wresni.csg.files.loaders;

import java.nio.file.Path;

public interface FileLoader {
    Path loadFile(String filePath);
}
