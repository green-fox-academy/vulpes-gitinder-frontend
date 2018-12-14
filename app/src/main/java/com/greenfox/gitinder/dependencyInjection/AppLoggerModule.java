package com.greenfox.gitinder.dependencyInjection;


import android.util.Log;


import com.greenfox.gitinder.Model.TestClass;

import dagger.Module;
import dagger.Provides;

@Module
public class AppLoggerModule {

    @Provides
    public TestClass getTestClass() {
        Log.d("AppTest", "Bla bla bla");

        return new TestClass();
    }
}
