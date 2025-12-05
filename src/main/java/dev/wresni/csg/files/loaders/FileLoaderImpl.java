package dev.wresni.csg.files.loaders;

import dev.wresni.csg.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileLoaderImpl implements FileLoader {
    @Value("${app.directory.path:}")
    private String dirPath;

    @Override
    public Path loadFile(String fileName) {
        String filePath = StringUtil.isBlank(dirPath) ? fileName : String.join("/", dirPath, fileName);
        log.debug("[LOADING_FILE] Loading file: {}", filePath);
        if (StringUtil.isBlank(fileName)) return invalidBecauseOfBlank();

        Path path = Path.of(filePath);
        boolean isFileExist = Files.exists(path);
        if (!isFileExist) return invalidBecauseOfNotExists(fileName);

        log.debug("[LOADING_FILE] File {} exists", fileName);
        return path;
    }

    private Path invalidBecauseOfNotExists(String filePath) {
        log.error("[LOADING_FILE] Skipping... file {} does not exists!", filePath);
        return null;
    }

    private Path invalidBecauseOfBlank() {
        log.error("[LOADING_FILE] Skipping file because of blank");
        return null;
    }
}
