package com.sexton.example.spring.expandable.fields.example.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.sexton.example.spring.expandable.fields.example.serialization.support.PropertyChain;
import com.sexton.example.spring.expandable.fields.example.serialization.support.StringPropertyChain;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExpandableFieldSerializer extends StdSerializer<Object> {
    private final List<PropertyChain> propertyChainList;

    public ExpandableFieldSerializer(final Set<String> expandFieldNames) {
        super(Object.class);

        this.propertyChainList = expandFieldNames.stream()
                .map(StringPropertyChain::fromNamePath)
                .collect(Collectors.toList());
    }

    @Override
    public void serialize(final Object o, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        final PropertyChain currentNamePath = getCurrentNamePath(jsonGenerator);

        final boolean isWantedPath = propertyChainList.stream().anyMatch(chain -> chain.containsSubChainIgnoreCase(currentNamePath));
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

    private PropertyChain getCurrentNamePath(final JsonGenerator jsonGenerator) {
        return getCurrentNamePath(jsonGenerator.getOutputContext(), new LinkedList<>());
    }

    private PropertyChain getCurrentNamePath(final JsonStreamContext jsonStreamContext, final Deque<String> namePath) {
        if (jsonStreamContext == null) {
            return new StringPropertyChain(namePath);
        }

        final String currentName = jsonStreamContext.getCurrentName();
        if (currentName != null) {
            namePath.addFirst(jsonStreamContext.getCurrentName());
        }

        return getCurrentNamePath(jsonStreamContext.getParent(), namePath);
    }
}
