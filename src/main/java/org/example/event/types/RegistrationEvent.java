package org.example.event.types;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Qualifier
@Retention(value = RUNTIME)
@Target({ TYPE, METHOD, PARAMETER, FIELD })
public @interface RegistrationEvent {
    // intentionally left empty - used to group registration events
}
