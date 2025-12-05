package dev.wresni.csg.files.readers;

import dev.wresni.csg.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Objects;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class FileReaderFactory implements FileReader {
    private final FileReaderHtml htmlReader;
    private final FileReaderTxt txtReader;

    @Override
    public void readFile(Path filepath, Collection<Rule> rules) {
        if (Objects.isNull(filepath)) return;

        switch (getExtension(filepath)) {
            case FileReaderHtml.EXT -> htmlReader.readFile(filepath, rules);
            case FileReaderTxt.EXT -> txtReader.readFile(filepath, rules);
            default -> {
                log.debug("[READING_FILE] file {} skipped because cannot be read", filepath.getFileName());
            }
        }
    }

    private String getExtension(Path filepath) {
        String fileName = filepath.getFileName().toString();
        int index = fileName.lastIndexOf('.');
        return index <= 0 ? "" : fileName.substring(index + 1);
    }
}
