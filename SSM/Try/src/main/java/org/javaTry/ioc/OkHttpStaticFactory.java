package org.javaTry.ioc;

import okhttp3.OkHttpClient;

public class OkHttpStaticFactory {
    public static OkHttpClient getInstance () {
        return new OkHttpClient.Builder().build();
    }

}
