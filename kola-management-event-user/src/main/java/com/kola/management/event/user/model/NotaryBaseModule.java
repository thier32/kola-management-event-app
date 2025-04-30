package com.notary.management.kernel.model;

import com.notary.management.kernel.INotaryModule;

import java.util.List;

public class NotaryBaseModule implements INotaryModule {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public List<String> getMenus() {
        return List.of();
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean isDisplayable() {
        return false;
    }
}
