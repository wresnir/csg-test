package dev.wresni.csg.rules;

import dev.wresni.csg.utils.StringUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Slf4j
@Getter
@Order(2)
@Component
@NoArgsConstructor
public class RuleWordsLongerThan5 extends Rule {
    private Set<String> words = new LinkedHashSet<>();

    @Override
    public void process(String word) {
        if (StringUtil.nonBlank(word) && word.length() > 5) words.add(word);
    }

    @Override
    public void present() {
        log.info("[RESULT] Words that are longer than 5 are {}", words);
    }

    @Override
    public void reset() {
        words.clear();
    }
}
