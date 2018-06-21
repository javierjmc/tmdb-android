package com.example.themoviedb.dagger2.qualifiers;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Qualifier to represent an api endpoint.
 */
@Qualifier
@Retention(RUNTIME)
public @interface ApiEndpoint {
}
