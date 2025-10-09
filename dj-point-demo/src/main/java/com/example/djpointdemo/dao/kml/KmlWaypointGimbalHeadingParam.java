package com.example.djpointdemo.dao.kml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("wpml:waypointGimbalHeadingParam")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KmlWaypointGimbalHeadingParam {

    @XStreamAlias("wpml:waypointGimbalPitchAngle")
    private String waypointGimbalPitchAngle;

    @XStreamAlias("wpml:waypointGimbalYawAngle")
    private String waypointGimbalYawAngle;


}
