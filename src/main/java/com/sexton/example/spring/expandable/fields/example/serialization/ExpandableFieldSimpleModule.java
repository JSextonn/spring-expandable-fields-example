package com.sexton.example.spring.expandable.fields.example.serialization;

import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Set;

public class ExpandableFieldSimpleModule extends SimpleModule {
    private final Set<String> expandFieldNames;

    public ExpandableFieldSimpleModule(final Set<String> expandFieldNames) {
        this.expandFieldNames = expandFieldNames;
    }

    @Override
    public void setupModule(final SetupContext context) {
        super.setupModule(context);

        context.addBeanSerializerModifier(new ExpandableFieldBeanSerializerModifier(expandFieldNames));
    }
}
