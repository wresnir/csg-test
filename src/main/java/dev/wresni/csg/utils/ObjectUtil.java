package dev.wresni.csg.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectUtil {
    public static <T> boolean validate(T obj, Predicate<T> predicate, Logger log, String message, Object... logObjs) {
        boolean result = Objects.nonNull(predicate) && predicate.test(obj);
        if (Objects.isNull(log)) return result;

        if (!result && Objects.nonNull(logObjs)) log.error(message, logObjs);
        else if (!result) log.error(message);
        return result;
    }

    public static <T> boolean validate(T obj, Predicate<T> predicate, Logger log, String message) {
        return validate(obj, predicate, log, message, (Object) null);
    }
}
