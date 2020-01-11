package com.example.userservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.javafx.jmx.json.JSONException;
import org.h2.util.json.JSONArray;

public class JSONUtils {
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
