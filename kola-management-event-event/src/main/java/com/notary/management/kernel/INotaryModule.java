package com.notary.management.kernel;

import java.util.List;

public interface INotaryModule {
    String getName();
    List<String> getMenus();
    boolean isEnabled();
    boolean isDisplayable();
}
