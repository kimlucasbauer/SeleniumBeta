package utils.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.*;

public class JsonGenerator {

    private Object structure;
    private final ObjectMapper mapper = new ObjectMapper();

    public void add(Object value) {
        Object converted = convertToMapIfPojo(value);

        if (structure == null) {
            structure = converted;
        } else if (structure instanceof Map && converted instanceof Map) {
            ((Map<String, Object>) structure).putAll((Map<String, Object>) converted);
        } else if (structure instanceof List) {
            ((List<Object>) structure).add(converted);
        } else {
            structure = converted;
        }
    }

    public void add(String reference, Object value) {
        Object converted = convertToMapIfPojo(value);

        if (structure == null) {
            structure = new HashMap<String, Object>();
            ((Map<String, Object>) structure).put(reference, converted);
            return;
        }

        if (structure instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) structure;
            Object existing = map.get(reference);

            if (existing == null) {
                map.put(reference, converted);
            } else if (existing instanceof List) {
                ((List<Object>) existing).add(converted);
            } else if (existing instanceof Map && converted instanceof Map) {
                ((Map<String, Object>) existing).putAll((Map<String, Object>) converted);
            } else {
                List<Object> list = new ArrayList<>();
                list.add(existing);
                list.add(converted);
                map.put(reference, list);
            }

        } else if (structure instanceof List) {
            List<Object> list = (List<Object>) structure;
            int index;

            try {
                index = Integer.parseInt(reference);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Reference must be a numeric index when structure is a list.");
            }

            while (list.size() <= index) {
                list.add(new HashMap<String, Object>());
            }

            Object item = list.get(index);
            Object itemConverted = convertToMapIfPojo(item);

            if (itemConverted instanceof Map && converted instanceof Map) {
                ((Map<String, Object>) itemConverted).putAll((Map<String, Object>) converted);
                list.set(index, itemConverted);
            } else {
                list.set(index, converted);
            }

        } else {
            throw new IllegalStateException("Unsupported structure type.");
        }
    }

    public String buildJson() {
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return mapper.writeValueAsString(structure);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Map<String, Object>> convertListToMapList(List<?> list) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object obj : list) {
            result.add(mapper.convertValue(obj, Map.class));
        }
        return result;
    }

    private Object convertToMapIfPojo(Object value) {
        if (value instanceof Map || value instanceof List || value instanceof String || value instanceof Number || value instanceof Boolean) {
            return value;
        }
        return mapper.convertValue(value, Map.class);
    }
}