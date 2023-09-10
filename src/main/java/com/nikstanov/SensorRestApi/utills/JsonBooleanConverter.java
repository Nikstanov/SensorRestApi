package com.nikstanov.SensorRestApi.utills;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Set;

import static com.fasterxml.jackson.core.JsonToken.VALUE_FALSE;
import static com.fasterxml.jackson.core.JsonToken.VALUE_TRUE;

public class JsonBooleanConverter extends JsonDeserializer<Boolean> {
    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (Set.of(VALUE_FALSE, VALUE_TRUE).contains(p.getCurrentToken())) {
            return p.getValueAsBoolean();
        }
        else{
            throw new IllegalArgumentException();
        }
    }
}
