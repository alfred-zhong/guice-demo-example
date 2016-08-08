package com.snowinpluto.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.io.CharStreams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static com.google.common.base.Preconditions.checkNotNull;

public class HttpServletUtil {

    /**
     * 转换 HttpServletRequest 中的 Body 字符串
     * @param request
     * @return
     */
    public static String parseBody(HttpServletRequest request) {
        checkNotNull(request);

        String body = null;

        BufferedReader reader = null;
        try {
            reader = request.getReader();
            if (reader != null) {
                body = CharStreams.toString(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return body;
    }

    /**
     *
     * @param response
     * @return
     */
    private static PrintWriter getWriter(HttpServletResponse response) {
        if (response == null)
            return null;

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return writer;
    }

    /**
     * description:send the ajax response back to the client side
     * @param responseObj
     * @param writer
     */
    private static void writeAjaxJSONResponse(Object responseObj, PrintWriter writer) {
        if (writer == null || responseObj == null) {
            return;
        }
        try {
            writer.write(JSON.toJSONString(responseObj, SerializerFeature.DisableCircularReferenceDetect));
        } finally {
            writer.flush();
            writer.close();
        }
    }

    /**
     * description:send the ajax response back to the client side.
     *
     * @param responseObj
     * @param response
     */
    public static void writeAjaxJSONResponse(Object responseObj, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
        // 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies.
        response.setContentType("application/json");

        PrintWriter writer = getWriter(response);
        writeAjaxJSONResponse(responseObj, writer);
    }

    public static void writeStringResponse(String text, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
        // 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies.
        response.setContentType("text/plain");

        PrintWriter writer = getWriter(response);
        try {
            writer.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            writer.flush();
            writer.close();
        }
    }
}
