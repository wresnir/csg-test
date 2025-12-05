package dev.wresni.csg;

import dev.wresni.csg.files.loaders.FileLoader;
import dev.wresni.csg.files.readers.FileReader;
import dev.wresni.csg.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final FileLoader fileLoader;
    private final FileReader fileReader;
    private final List<Rule> rules;

    @Value("${app.mode:all}")
    private String mode;

    @Override
    public void run(String... args) {
        switch (mode) {
            case "each" -> runEach(args);
            default -> runAll(args);
        }
        System.exit(0);
    }

    private void runEach(String[] args) {
        Arrays.stream(args)
                .map(fileLoader::loadFile)
                .filter(Objects::nonNull)
                .forEach(file -> {
                    fileReader.readFile(file, rules);
                    log.info("[RESULT] the result of file {}", file.getFileName());
                    rules.forEach(Rule::presentAndReset);
                });
    }

    private void runAll(String[] args) {
        Set<String> filenames = Arrays.stream(args)
                .map(fileLoader::loadFile)
                .filter(Objects::nonNull)
                .map(file -> {
                    fileReader.readFile(file, rules);
                    return file.getFileName().toString();
                })
                .collect(Collectors.toSet());

        log.info("[RESULT] the result of files {}", filenames);
        rules.forEach(Rule::presentAndReset);
    }
}
