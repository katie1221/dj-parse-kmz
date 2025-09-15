package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("LinearRing")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlLinearRing {

    @XStreamAlias("coordinates")
    private String coordinates;
}
