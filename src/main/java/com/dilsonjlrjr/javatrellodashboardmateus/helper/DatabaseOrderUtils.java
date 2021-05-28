package com.dilsonjlrjr.javatrellodashboardmateus.helper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.annotation.FieldName;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Component
public class DatabaseOrderUtils {

    public String doCreateStringOrdebyDatabase(Pageable pageable, Class<?> classObject) {
        Map<String, String> attributeDatabase = new HashMap<>();
        StringBuilder orderDatabase = new StringBuilder();

        for (Field field : classObject.getDeclaredFields()) {
            if (field.isAnnotationPresent(FieldName.class)) {
                attributeDatabase.put(field.getName(), field.getAnnotation(FieldName.class).value());
            }
        }

        for(Sort.Order order : pageable.getSort()) {
            if (attributeDatabase.containsKey(order.getProperty())) {
                orderDatabase.append(attributeDatabase.get(order.getProperty())).append(" ").append(order.getDirection().name().toLowerCase()).append(",");
            }
        }

        return (orderDatabase.length() > 0 ? orderDatabase.substring(0, orderDatabase.length() - 1): "");
    }
}
