package com.greenfox.gitinder.dependencyInjection;




import com.greenfox.gitinder.Model.TestClass;

import dagger.Component;

@Component(modules = AppLoggerModule.class)
public interface AppComponent {
    TestClass getTestClass();

}
