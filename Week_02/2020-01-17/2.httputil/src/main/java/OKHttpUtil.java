import okhttp3.*;

import java.io.IOException;

public class OKHttpUtil {
    private static  OkHttpClient client = new OkHttpClient();
    public static void main(String[] args) throws IOException {

        String url = "http://localhost:8808/test";
        String result = getMethod(url);
        System.out.println(result);
    }

     static String getMethod(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try(Response response = client.newCall(request).execute()){
            return response.body().string();
        }
    }

     static String postMethod(String url,String json) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json,mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try(Response response = client.newCall(request).execute()){
            return response.body().string();
        }
    }
}
