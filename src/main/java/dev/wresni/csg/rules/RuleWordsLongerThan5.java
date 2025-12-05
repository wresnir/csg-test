package dev.wresni.csg.rules;

import dev.wresni.csg.utils.StringUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Order(2)
@Component
@NoArgsConstructor
public class RuleWordsLongerThan5 extends Rule {
    @Override
    public boolean isValid(String word) {
        return StringUtil.nonBlank(word) && word.length() > 5;
    }

    @Override
    public void present() {
        log.info("[RESULT] Words that are longer than 5 are {}", wordsCount.keySet());
    }
}
