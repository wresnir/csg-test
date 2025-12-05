package dev.wresni.csg.rules;

public abstract class Rule {
    public abstract void process(String word);
    public abstract void present();
    public abstract void reset();
    public void presentAndReset() {
        present();
        reset();
    }
}
