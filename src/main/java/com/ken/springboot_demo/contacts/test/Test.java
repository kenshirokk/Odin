package com.ken.springboot_demo.contacts.test;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.ProxyOptions;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import okhttp3.*;
import org.apache.tomcat.util.buf.HexUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Test {
    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString();
    }
    public static void main(String[] args) throws Exception {
        m1();


    }

    private static void m3() throws NoSuchAlgorithmException, InvalidKeyException {
        String url = "https://picaapi.picacomic.com/auth/sign-in";
        long time = System.currentTimeMillis() / 1000;
        String newurl = url.replace("https://picaapi.picacomic.com/", "");
        String nonce = UUID.randomUUID().toString().replace("-", "");
        String apikey = "C69BAF41DA5ABD1FFEDC6D2FEA56B";
        String ss = "~d}$Q7$eIni=V)9\\\\RK/P.RM4;9[7|@/CA}b~OW!3?EV`:<>M7pddUBL5n|0/*Cn";
//        String raw = newurl + time + nonce + "POST" + apikey;
        String raw = url+newurl + time + nonce + "POST" + apikey +".2.1.3.3.445";

        raw = raw.toLowerCase();
        SecretKeySpec signingKey = new SecretKeySpec(ss.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] bytes = mac.doFinal(raw.getBytes());

        String sigr = HexUtils.toHexString(bytes);

        Vertx vertx = Vertx.vertx();
        WebClientOptions option = new WebClientOptions();
        option.setTrustAll(true);
        option.setVerifyHost(false);
        option.setProxyOptions(new ProxyOptions().setHost("127.0.0.1").setPort(1081));
        WebClient client = WebClient.create(vertx, option);

        JsonObject json = new JsonObject();
        json.put("email", "weilemfc002");
        json.put("password", "weilemfc002");
        client.post(url)
                .putHeader("Content-Type", "application/json; charset=UTF-8")
                .putHeader("accept", "application/vnd.picacomic.com.v1+json")
                .putHeader("signature", sigr)
                .putHeader("api-key", apikey)
                .putHeader("authorization", "")
                .putHeader("app-channel", "3")
                .putHeader("time", time + "")
                .putHeader("app-uuid", "defaultUuid")
                .putHeader("nonce", nonce)
                .putHeader("app-version", "2.2.1.3.3.4")
                .putHeader("image-quality", "original")
                .putHeader("app-platform", "android")
                .putHeader("app-build-version", "45")
                .putHeader("user-agent", "okhttp/3.8.1")
                .sendJsonObject(json).onSuccess(res -> {
                    System.out.println(123123123);
                    System.out.println(res.statusCode());
                    System.out.println(res.statusMessage());
                    System.out.println(res.bodyAsString());
                }).onFailure(Throwable::printStackTrace);
    }

    private static void m2() throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        String url = "https://picaapi.picacomic.com/auth/sign-in";
        long time = System.currentTimeMillis() / 1000;
        String newurl = url.replace("https://picaapi.picacomic.com/", "");
        String nonce = UUID.randomUUID().toString().replace("-", "");
        String apikey = "C69BAF41DA5ABD1FFEDC6D2FEA56B";
        String ss = "~d}$Q7$eIni=V)9\\\\RK/P.RM4;9[7|@/CA}b~OW!3?EV`:<>M7pddUBL5n|0/*Cn";
        String raw = newurl + time + nonce + "POST" + apikey;

        raw = raw.toLowerCase();
        SecretKeySpec signingKey = new SecretKeySpec(ss.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] bytes = mac.doFinal(raw.getBytes());

        String sigr = HexUtils.toHexString(bytes);


        String json = new StringBuilder()
                .append("{")
                .append("\"email\":\"weilemfc002\",")
                .append("\"password\":\"weilemfc002\"")
                .append("}").toString();

        MediaType JSON = MediaType.get("application/json; charset=utf-8");
//        OkHttpClient client = new OkHttpClient();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.proxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1",1080)));
        OkHttpClient client = builder.build();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)

                .header("Content-Type", "application/json; charset=UTF-8")
                .header("accept", "application/vnd.picacomic.com.v1+json")
                .header("signature", sigr)
                .header("api-key", apikey)
                .header("authorization", "")
                .header("app-channel", "3")
                .header("time", time+"")
                .header("app-uuid", "defaultUuid")
                .header("nonce", nonce)
                .header("app-version", "2.2.1.3.3.4")
                .header("image-quality", "original")
                .header("app-platform", "android")
                .header("app-build-version", "45")
//                .header("user-agent", "okhttp/3.8.1")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }

    private static void m1() throws NoSuchAlgorithmException, InvalidKeyException, IOException,
            InterruptedException {
        String url = "https://picaapi.picacomic.com/auth/sign-in";
        long time = System.currentTimeMillis() / 1000;
        String newurl = url.replace("https://picaapi.picacomic.com/", "");
        String nonce = UUID.randomUUID().toString().replace("-", "");
        String apikey = "C69BAF41DA5ABD1FFEDC6D2FEA56B";
        String ss = "~d}$Q7$eIni=V)9\\\\RK/P.RM4;9[7|@/CA}b~OW!3?EV`:<>M7pddUBL5n|0/*Cn";
//        String raw = newurl + time + nonce + "POST" + apikey;
        String raw = url+newurl + time + nonce + "POST" + apikey +".2.1.3.3.445";

        raw = raw.toLowerCase();
        SecretKeySpec signingKey = new SecretKeySpec(ss.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] bytes = mac.doFinal(raw.getBytes());

        String sigr = HexUtils.toHexString(bytes);


        String json = new StringBuilder()
                .append("{")
                .append("\"email\":\"weilemfc002\",")
                .append("\"password\":\"weilemfc002\"")
                .append("}").toString();


        HttpClient client = HttpClient.newBuilder()
                .proxy(ProxySelector.of(new InetSocketAddress(1081)))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("accept", "application/vnd.picacomic.com.v1+json")
                .header("signature", sigr+"1")
                .header("api-key", apikey)
                .header("app-channel", "3")
                .header("time", time+"")
                .header("app-uuid", "defaultUuid")
                .header("nonce", nonce)
                .header("app-version", "2.2.1.3.3.4")
                .header("image-quality", "original")
                .header("app-platform", "android")
                .header("app-build-version", "45")
                .header("user-agent", "okhttp/3.8.1")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body().toString());
    }
}
