package iuh.week02_lab_huynhhoangphuc_21036541.backend.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ext.ContextResolver;

public class ObjectmapperContextResolver implements ContextResolver<ObjectMapper> {
    final ObjectMapper mapper = new ObjectMapper();

    public ObjectmapperContextResolver() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return mapper;
    }
}
