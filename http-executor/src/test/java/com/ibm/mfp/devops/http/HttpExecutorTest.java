package com.ibm.mfp.devops.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by issaharw on 7.8.2016.
 */

public class HttpExecutorTest {

    @Test
    public void testSimpleGet() {
        try {
            String s = HttpExecutor.executeHttpRequest("http://httpbin.org/get", "GET");
            Map<String,Object> result = new ObjectMapper().readValue(s, HashMap.class);
            System.out.println(result);

            Assert.assertNotNull(result.get("headers"));

        } catch (IOException e) {
            Assert.fail("There shouldn't be an exception");
        }
    }

    @Test
    public void testGetWithParams() {
        try {
            String s = HttpExecutor.executeHttpRequest("http://httpbin.org/get?a=1", "GET");
            Map<String,Object> result = new ObjectMapper().readValue(s, HashMap.class);
            System.out.println(result);
            Map<String,Object> args = (Map<String, Object>) result.get("args");

            Assert.assertTrue(args.get("a").equals("1"));

        } catch (IOException e) {
            Assert.fail("There shouldn't be an exception");
        }
    }

    @Test
    public void testPostShouldFail() {
        try {
            String s = HttpExecutor.executeHttpRequest("http://nba.com", "POST");

            Assert.fail("We shouldn't reach here.");
        }
        catch (Exception e) {
            Assert.assertTrue("We should have received an unsupported operation exceptoin", e instanceof UnsupportedOperationException);
        }

    }
}
