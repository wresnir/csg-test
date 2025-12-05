package dev.wresni.csg.files.readers;

import dev.wresni.csg.rules.Rule;

import java.nio.file.Path;
import java.util.Collection;

public interface FileReader {
    void readFile(Path filepath, Collection<Rule> rules);
}
