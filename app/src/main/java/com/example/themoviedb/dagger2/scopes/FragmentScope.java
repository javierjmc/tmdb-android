package com.example.themoviedb.dagger2.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Scope for components expected to be around for the lifetime of a {@link android.app.Fragment}
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
