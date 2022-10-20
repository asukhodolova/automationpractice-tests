package com.solvd.mydemoapp.mobile.pages.common;

public interface INavigationMenu {

    CartScreenBase openCart();

    int getProductsAmountInCart();

    boolean isNoCartCounter();
}
