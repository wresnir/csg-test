package dev.wresni.csg.rules;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public abstract class Rule {
    protected Map<String, Long> wordsCount = new LinkedHashMap<>();

    public abstract boolean isValid(String word);
    public abstract void present();
    public void reset() {
        wordsCount.clear();
    }
    public void process(String word) {
        if (isValid(word)) wordsCount.merge(word, 1L, Long::sum);
    }
    public void presentAndReset() {
        present();
        reset();
    }
}
