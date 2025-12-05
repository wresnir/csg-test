package dev.wresni.csg.rules;

import dev.wresni.csg.utils.StringUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Order(1)
@Component
@NoArgsConstructor
public class RuleWordsWithUpperCase extends Rule {
    private Map<String, Long> countMap = new HashMap<>();

    @Override
    public void process(String word) {
        if (StringUtil.isBlank(word) || !Character.isUpperCase(word.charAt(0))) return;
        countMap.merge(word, 1L, Long::sum);
    }

    @Override
    public void present() {
        log.info("[RESULT] Words that start with an upper case is {}", countMap.values().stream().reduce(0L, Long::sum));
        log.debug("[RESULT] Words that start with an upper case {}", countMap.keySet());
    }

    @Override
    public void reset() {
        countMap.clear();
    }
}
