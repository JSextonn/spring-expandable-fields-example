package com.sexton.example.spring.expandable.fields.example.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

public class ExpandableFieldSerializer extends StdSerializer<Object> {
    private final Set<String> expandFieldPathNames;

    public ExpandableFieldSerializer(final Set<String> expandFieldPathNames) {
        super(Object.class);

        this.expandFieldPathNames = expandFieldPathNames;
    }

    @Override
    public void serialize(final Object o, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        final String currentNamePath = getCurrentNamePath(jsonGenerator);

        final boolean isWantedPath = isWantedPath(currentNamePath);
        if (isWantedPath) {
            jsonGenerator.writeObject(o);
        } else {
            if (o == null) {
                jsonGenerator.writeNull();
            } else {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeEndObject();
            }
        }
    }

    private boolean isWantedPath(final String namePath) {
        final String lowerCaseNamePath = namePath.toLowerCase();

        return expandFieldPathNames.stream()
                .map(String::toLowerCase)
                .anyMatch(chain -> chain.startsWith(lowerCaseNamePath));
    }

    private String getCurrentNamePath(final JsonGenerator jsonGenerator) {
        return getCurrentNamePath(jsonGenerator.getOutputContext(), new LinkedList<>());
    }

    private String getCurrentNamePath(final JsonStreamContext jsonStreamContext, final Deque<String> namePath) {
        if (jsonStreamContext == null) {
            return String.join(".", namePath);
        }

        final String currentName = jsonStreamContext.getCurrentName();
        if (currentName != null) {
            namePath.addFirst(jsonStreamContext.getCurrentName());
        }

        return getCurrentNamePath(jsonStreamContext.getParent(), namePath);
    }
}
