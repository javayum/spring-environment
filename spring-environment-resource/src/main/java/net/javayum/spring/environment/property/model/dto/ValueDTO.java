package net.javayum.spring.environment.property.model.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import net.javayum.spring.environment.property.model.Value;

import java.io.Serializable;

public class ValueDTO implements Value, Serializable {

    private final String value;

    private ValueDTO(String value) {
        this.value = value;
    }

    public static Value of(String value) {
        return new ValueDTO(value);
    }

    @JsonGetter("stringValue")
    public String toStringValue() {
        return value;
    }
}
