package dev.wresni.csg.files.readers;

import dev.wresni.csg.rules.Rule;
import dev.wresni.csg.utils.ObjectUtil;
import dev.wresni.csg.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileReaderTxt implements FileReader {

    @Override
    public void readFile(Path filepath, Collection<Rule> rules) {
        if (!isFilePathValid(filepath)) return;

        try (BufferedReader reader = Files.newBufferedReader(filepath)) {
            log.debug("[READING_FILE] file {} start being read", filepath.getFileName());
            reader.lines().forEach(line -> readFile(line, rules));
            log.debug("[READING_FILE] file {} has been read", filepath.getFileName());
        } catch (IOException e) {
            log.error("[READING_FILE] file {} error while reading", filepath.getFileName(), e);
        }
    }

    private boolean isFilePathValid(Path filepath) {
        return ObjectUtil.validate(filepath, Objects::nonNull, log, "[READING_FILE] file is null") &&
                ObjectUtil.validate(filepath, Files::exists, log, "[READING_FILE] file {} is not exists", filepath.getFileName());
    }

    private void readFile(String line, Collection<Rule> rules) {
        if (StringUtil.isBlank(line))
            log.debug("[READING_FILE] empty line");
        else
            Arrays.stream(line.split("[^A-Za-z0-9]+"))
                .filter(StringUtil::nonBlank)
                .forEach(word -> rules.forEach(rule -> rule.process(word)));
    }
}
