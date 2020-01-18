package santhosh.pm.ormorange.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Column of the table with name, unique and notnull parameters
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    String name();

    /**
     * @return
     */
    boolean unique() default false;

    /**
     * @return
     */
    boolean notNull() default false;
}
