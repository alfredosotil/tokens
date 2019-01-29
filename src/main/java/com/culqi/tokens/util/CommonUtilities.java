package com.culqi.tokens.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtilities {

    private static Logger logger = LoggerFactory.getLogger(CommonUtilities.class);

    /**
     *
     * @param date
     * @param format
     * @return
     */
    public static LocalDateTime getStringToLocalDateTime(String date, String format) {

        logger.info("String date {} with format {}", date, format);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = null;
        try {
            if (date.compareTo("now") == 0) {
                localDateTime = LocalDateTime.now();
            } else {
                LocalDate localDate = LocalDate.parse(date, formatter);
                localDateTime = localDate.atStartOfDay();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localDateTime;
    }

    /**
     *
     * @param object
     * @return
     */
    public static String ObjectToJsonString(Object object) {
        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(object);
            logger.info("JSON = {}", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     *
     * @param bytes
     * @return
     */
    public static JSONObject BytesToJsonObject(byte[] bytes){
        return new JSONObject(new String(bytes));
    }

}
