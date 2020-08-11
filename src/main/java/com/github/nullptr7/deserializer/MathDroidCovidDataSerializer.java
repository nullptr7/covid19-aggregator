package com.github.nullptr7.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.nullptr7.model.mathdroid.MathDroidCovidData;

import java.io.IOException;

public class MathDroidCovidDataSerializer extends JsonDeserializer<MathDroidCovidData> {


    @Override
    public MathDroidCovidData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec codec = p.getCodec();
        final JsonNode node = codec.readTree(p);

        return MathDroidCovidData.builder()
                                 .confirmed(node.get("confirmed")
                                       .get("value")
                                       .asLong())
                                 .deaths(node.get("deaths")
                                    .get("value")
                                    .asLong())
                                 .recovered(node.get("recovered")
                                       .get("value")
                                       .asLong())
                                 .build();
    }
}
