package com.ibm.mfp.devops.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by issaharw on 7.8.2016.
 */
public class HttpExecutor {

    public static String executeHttpRequest(String url, String method) throws IOException {
        if (!"GET".equalsIgnoreCase(method)) {
            throw new UnsupportedOperationException("This method supports only GET.");
        }

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        InputStream content = entity.getContent();
        String result = IOUtils.toString(content, "UTF-8");
        IOUtils.closeQuietly(content);

        return result;

    }
}
