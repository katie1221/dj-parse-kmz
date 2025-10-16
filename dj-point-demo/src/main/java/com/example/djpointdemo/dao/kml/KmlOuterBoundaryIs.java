package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("outerBoundaryIs")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlOuterBoundaryIs {

    @XStreamAlias("LinearRing")
    private KmlLinearRing linearRing;
}
