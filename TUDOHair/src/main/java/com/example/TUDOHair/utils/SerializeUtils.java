package com.example.TUDOHair.utils;

import com.google.gson.*;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
@Slf4j
public class SerializeUtils {

    class LocalDateTypeAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

        private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(), formatter);

        }

        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.format(formatter));
        }
    }

    class LocalDateTimeTypeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }

        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT,
                                         JsonDeserializationContext context) throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(), formatter);
        }
    }

    class ZonedDateTimeTypeAdapter implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

        @Override
        public JsonElement serialize(ZonedDateTime zonedDateTime, Type srcType,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(zonedDateTime));
        }

        @Override
        public ZonedDateTime deserialize(JsonElement json, Type typeOfT,
                                         JsonDeserializationContext context) throws JsonParseException {
            return ZonedDateTime.parse(json.getAsString(), formatter);
        }
    }

    class InstantTypeAdapter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

        @Override
        public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
            String formattedString = formatter.format(src);
            return new JsonPrimitive(formattedString);
        }

        @Override
        public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return Instant.parse(json.getAsString());
        }
    }

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter())
            .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
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

    public static Map<String, Object> flatMapJson(String input, String joinSymbol) {
        Map<String, Object> map = new HashMap<>();
        JsonObject jsonObject;
        log.info("SerializeUtils flatMapJson ( input = {} , joinSymbol = {} )", input, joinSymbol);
        try {
            jsonObject = JsonParser.parseString(input).getAsJsonObject();
        } catch (Exception e) {
            log.error("Error: ", e);
            return new HashMap<>();
        }

        travel(joinSymbol, "", map, jsonObject);
        return map;
    }

    private static void travel(String joinSymbol, String parentPath, Map<String, Object> map, JsonObject jsonObject) {
        for (Map.Entry<String, JsonElement> key : jsonObject.entrySet()) {
            String currentPath;
            if (!parentPath.isBlank()) {
                currentPath = parentPath + joinSymbol + key.getKey();
            } else {
                currentPath = key.getKey();
            }
            if (!key.getValue().isJsonObject()) {
                map.put(currentPath, key.getValue().getAsString());
            } else {
                travel(joinSymbol, currentPath, map, key.getValue().getAsJsonObject());
            }
        }
    }

    public static JsonElement toJsonTree(Object object) {
        return gson.toJsonTree(object);
    }

    public static <T> T fromJson(JsonElement jsonElement, Class<T> targetClass) {
        return gson.fromJson(jsonElement, targetClass);
    }

    public static <T> String toJson(Object entity, Class<T> targetClass) {
        return gson.toJson(entity, targetClass);
    }

}
