package com.oril.service;

import com.oril.exceptions.LookupException;
import com.oril.exceptions.ServiceUnavailableException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class DomainVerificationImpl implements DomainVerification {


    private static final String DEFAULT_API_URL_FORMAT = "https://sb-ssl.google.com/safebrowsing/api/lookup?client=%s&apikey=%s&appver=%s&pver=3.1&url=";
    private static final String API_KEY = "ABQIAAAAPXUJHx8mZ6CAQsqzkH7z4xS0xaBJ_pvoMJCKdO9XAHu-bmpUvw";

    private final String client;
    private final String apiKey;
    private final String appver;
    private final String urlFormat;

    public DomainVerificationImpl() {
        this("api",  "1.0");
    }

    public DomainVerificationImpl(String client, String appver) {
        this.client = client;
        this.apiKey = API_KEY;
        this.appver = appver;
        try {
            this.urlFormat = String.format(DEFAULT_API_URL_FORMAT,
                    URLEncoder.encode(client, "utf-8"),
                    URLEncoder.encode(apiKey, "utf-8"),
                    URLEncoder.encode(appver, "utf-8"), "%s");
        } catch (UnsupportedEncodingException ex) {
            //The system is supposed to support UTF-8
            throw new AssertionError(ex);
        }
    }


    public boolean lookupURL(String url_in) throws IOException, ServiceUnavailableException, LookupException {
        String encoded_url = URLEncoder.encode(url_in, "utf-8");
        URL url = new URL(urlFormat + encoded_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setUseCaches(false);
        conn.connect();
        int code = conn.getResponseCode();
        boolean trusted;
        switch (code) {
            case 200:
                trusted = false;
                break;
            case 204:
                trusted = true;
                break;
            case 400:
                throw new LookupException("Error 400: Bad Request");
            case 401:
                throw new LookupException("Error 401: Not Authorized");
            case 503:
                throw new ServiceUnavailableException("Error 503: Service Unavailable");
            default:
                throw new LookupException("Unknown response code: " + code);
        }
        conn.disconnect();
        return trusted;
    }

}
