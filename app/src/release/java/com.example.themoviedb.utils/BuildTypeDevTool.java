package com.example.themoviedb.utils;

import android.app.Application;

/**
 * Debug methods for release builds.
 */
class BuildTypeDevTool implements DevToolInterface {

    @Override
    public void initForApp(final Application app) {
        //NOOP
    }

    @Override
    public void injectDev(final Builder client) {
        //NOOP
    }

    @Override
    public void plantDevTree() {
        //NOOP
    }
}
