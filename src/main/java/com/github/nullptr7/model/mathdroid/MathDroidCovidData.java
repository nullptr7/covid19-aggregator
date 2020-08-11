package com.github.nullptr7.model.mathdroid;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.nullptr7.deserializer.MathDroidCovidDataSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = MathDroidCovidDataSerializer.class)
public class MathDroidCovidData {

    private Long confirmed;
    private Long recovered;
    private Long deaths;


}
