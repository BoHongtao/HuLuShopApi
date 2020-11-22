/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hulu.shop.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class HttpJsonMessageConverter extends AbstractHttpMessageConverter<Object> {
    private Gson gson = new Gson();
    public final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public HttpJsonMessageConverter() {
//        new MediaType("application", "json", DEFAULT_CHARSET);
        super(new MediaType("application", "json", DEFAULT_CHARSET), new MediaType("application", "*+json", DEFAULT_CHARSET));
    }

    @Override
    protected boolean supports(Class clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            System.out.println(">>> MyHttpMessageConverter.readInternal()");
            return gson.fromJson(convertStreamToString(inputMessage.getBody()), clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw new HttpMessageNotReadableException("Could not read JSON: " + e.getMessage(), e);
        }
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        System.out.println(">>> MyHttpMessageConverter.writeInternal()");
        String json;
        System.out.println("----------------");
        System.out.println(o.getClass().toString());
        if (o instanceof ResponseObject) {
            json = ((ResponseObject) o).converterToString();
        } else if (o instanceof String) {
            json = (String) o;
        } else {
            json = gson.toJson(o);
        }
        if (outputMessage != null) {
            outputMessage.getBody().write(json.getBytes());
            logger.info("[response] " + json);
        }
    }

    public String convertStreamToString(InputStream ins) throws IOException {
        if (ins == null) {
            return "";
        }
        StringWriter writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
            Integer n;
            n = reader.read(buffer);
            while (n != -1) {
                writer.write(buffer, 0, n);
                n = reader.read(buffer);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ins.close();
        }
        return writer.toString();
    }
}
