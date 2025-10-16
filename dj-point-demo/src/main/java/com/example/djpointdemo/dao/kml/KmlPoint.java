package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author qzz
 * @date 2025/8/12
 */
@Data
@XStreamAlias("Point")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlPoint {

    @XStreamAlias("coordinates")
    private String coordinates;
}
