package com.myapplication;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;


/**
 * Created by abhinav on 6/8/16.
 */

class networkhelper {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
}
}


