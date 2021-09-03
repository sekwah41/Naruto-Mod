package com.sekwah.sekclib.capabilitysync.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * For now these can only be primitives and strings.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Sync {

    int minTicks() default 3;

    /**
     * Should this be synced with other players
     * @return
     */
    boolean syncGlobally() default false;

}
