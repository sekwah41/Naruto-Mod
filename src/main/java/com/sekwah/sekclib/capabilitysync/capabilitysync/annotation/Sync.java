package com.sekwah.sekclib.capabilitysync.capabilitysync.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * For now these can only be primitives and strings.
 * Make sure that the Capabilities are not registered on EventPriority.LOWEST so that SekCLib can find it.
 *
 * Ensure that the same class is client and server side. The tags will be sorted alphabetically to try to help
 * however if you have different annotated tags e.g. different mod versions there will be sync issues.
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
