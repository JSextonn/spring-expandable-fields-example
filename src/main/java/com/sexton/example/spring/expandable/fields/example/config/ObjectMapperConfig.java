package com.sexton.example.spring.expandable.fields.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sexton.example.spring.expandable.fields.example.serialization.ExpandableFieldSimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Configuration
public class ObjectMapperConfig {
    private static final String EXPECTED_EXPAND_PARAMETER_NAME = "expand";

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ObjectMapper objectMapper(final HttpServletRequest httpServletRequest) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Set<String> expandableFieldNames = getExpandableFieldNamesFromRequest(httpServletRequest);
        objectMapper.registerModule(new ExpandableFieldSimpleModule(expandableFieldNames));

        return objectMapper;
    }

    private Set<String> getExpandableFieldNamesFromRequest(final HttpServletRequest request) {
        final String commaDelimitedListOfFieldNames = request.getParameter(EXPECTED_EXPAND_PARAMETER_NAME);
        return StringUtils.commaDelimitedListToSet(commaDelimitedListOfFieldNames);
    }
}
