package de.ufomc.config.core;

import java.lang.reflect.Field;

public class UfObject {

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("{");

        Field[] fields = this.getClass().getDeclaredFields();

        for (int i = 0; i != fields.length; i++) {
            Field field = fields[i];
            try {
                field.setAccessible(true);

                String fieldName = field.getName();
                Object fieldValue = field.get(this);
                String fieldType = field.getType().getSimpleName().toLowerCase();

                String valueRepresentation;

                if (fieldValue != null && ObjectCheck.isPrimitive(field.getType()) && !(fieldValue instanceof String)) {
                    valueRepresentation = fieldValue.toString();
                } else {
                    valueRepresentation = fieldValue != null ? fieldValue.toString() : "null";
                }

                s.append(fieldType)
                        .append(":")
                        .append(fieldName)
                        .append("=")
                        .append(valueRepresentation);

                if (fields.length - 1 != i){
                    s.append(",");
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        s.append("}");
        return s.toString();

    }

}
