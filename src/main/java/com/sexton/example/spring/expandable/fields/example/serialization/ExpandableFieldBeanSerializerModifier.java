package com.sexton.example.spring.expandable.fields.example.serialization;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.List;
import java.util.Set;

public class ExpandableFieldBeanSerializerModifier extends BeanSerializerModifier {
    private final Set<String> expandFieldNames;

    public ExpandableFieldBeanSerializerModifier(final Set<String> expandFieldNames) {
        this.expandFieldNames = expandFieldNames;
    }

    @Override
    public List<BeanPropertyWriter> changeProperties(final SerializationConfig config,
                                                     final BeanDescription beanDesc,
                                                     final List<BeanPropertyWriter> beanProperties) {
        for (final BeanPropertyWriter beanProperty : beanProperties) {
            final boolean isExpandableProperty = beanProperty.getAnnotation(JsonExpandableField.class) != null;
            if (isExpandableProperty) {
                final JsonSerializer<Object> jsonSerializer = new ExpandableFieldSerializer(expandFieldNames);
                beanProperty.assignSerializer(jsonSerializer);
            }
        }

        return beanProperties;
    }
}
