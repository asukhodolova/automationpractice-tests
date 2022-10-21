package com.solvd.mydemoapp.mobile.pages.common;

import org.apache.commons.lang3.NotImplementedException;

public interface ICommonScreen {

    default boolean isOpened() {
        throw new NotImplementedException("Method is not implemented for " + this.getClass());
    }

    default NavigationMenuBase getNavigation() {
        throw new NotImplementedException("Method is not implemented for " + this.getClass());
    }
}
