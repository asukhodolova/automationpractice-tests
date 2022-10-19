package com.solvd.mydemoapp.mobile.pages.common;

import com.solvd.mydemoapp.mobile.pages.common.CartPageBase;

public interface ICommonPage {

    CartPageBase openCart();

    int getProductsAmountInCart();
}
