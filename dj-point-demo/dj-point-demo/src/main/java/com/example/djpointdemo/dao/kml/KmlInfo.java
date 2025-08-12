package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

/**
 * @author qzz
 * @date 2025/8/12
 */
@Data
@XStreamAlias("kml")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlInfo {

    @XStreamAsAttribute
    @XStreamAlias("xmlns")
    private String xmlns = "http://www.opengis.net/kml/2.2";

    @XStreamAsAttribute
    @XStreamAlias("xmlns:wpml")
    private String wpml = "http://www.dji.com/wpmz/1.0.6";

    @XStreamAlias("Document")
    private KmlDocument document;
}
