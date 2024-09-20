package com.example.TUDOHair.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.time.*;

@UtilityClass
@Slf4j
public class SerializeUtils {

    private static final Gson gson = new GsonBuilder()
            .create();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> T fromJson(String targetObject, Class<T> targetClass) {
        return gson.fromJson(targetObject, targetClass);
    }

    public static <T> T fromJson(String targetObject, Type targetClass) {
        return gson.fromJson(targetObject, targetClass);
    }
}
