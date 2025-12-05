package dev.wresni.csg.rules;

import dev.wresni.csg.utils.StringUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Order(1)
@Component
@NoArgsConstructor
public class RuleWordsWithUpperCase extends Rule {
    @Override
    public boolean isValid(String word) {
        return StringUtil.nonBlank(word) && Character.isUpperCase(word.charAt(0));
    }

    @Override
    public void present() {
        log.info("[RESULT] Words that start with an upper case is {}", wordsCount.values().stream().reduce(0L, Long::sum));
        log.debug("[RESULT] Words that start with an upper case {}", wordsCount.keySet());
    }
}
