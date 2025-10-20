
## 接口参数示例

### 1. 生成航线 KMZ 文件

#### 1.1 航点航线 KMZ 文件 请求参数json
```json
{
  "templateType": "waypoint",
  "takeOffRefPoint": "22.582061,113.936558,12.140378",
  "droneType": 100,
  "subDroneType": 0,
  "payloadType": 98,
  "payloadPosition": 0,
  "imageFormat": "visable",
  "finishAction": "goHome",
  "exitOnRcLostAction": "goBack",
  "globalHeight": 100,
  "autoFlightSpeed": 10,
  "waypointHeadingReq": {
    "waypointHeadingMode": "followWayline"
  },
  "waypointTurnReq": {
    "waypointTurnMode": "toPointAndStopWithDiscontinuityCurvature"
  },
  "gimbalPitchMode": "manual",
  "startActionList": [
    {
      "actionIndex": 0,
      "gimbalYawRotateAngle": -90
    },
    {
      "actionIndex": 1,
      "hoverTime": 0.5
    }
  ],
  "routePointList": [
    {
      "routePointIndex": 0,
      "longitude": 113.935644171,
      "latitude": 22.584237204,
      "timeInterval": 2,
      "endIntervalRouteIndex": 0,
      "actions": [
        {
          "actionIndex": 0,
          "takePhotoType": 0,
          "useGlobalImageFormat": 1
        }
      ]
    },
    {
      "routePointIndex": 1,
      "longitude": 113.935826184,
      "latitude": 22.583696397
    },
    {
      "routePointIndex": 2,
      "longitude": 113.936790168,
      "latitude": 22.584005786
    }
  ]
}


```
#### 1.2 建图航拍 KMZ 文件 请求参数json
```json
{
  "templateType": "mapping2d",
  "takeOffRefPoint": "22.581115,113.940282,16.035026",
  "droneType": 91,
  "subDroneType": 1,
  "payloadType": 81,
  "payloadPosition": 0,
  "imageFormat": "visable,ir",
  "finishAction": "autoLand",
  "exitOnRcLostAction": "goBack",
  "globalHeight": 100,
  "autoFlightSpeed": 10,
  "waypointHeadingReq": {
    "waypointHeadingMode": "followWayline"
  },
  "waypointTurnReq": {
    "waypointTurnMode": "toPointAndStopWithDiscontinuityCurvature"
  },
  "gimbalPitchMode": "usePointSetting",
  "mappingTypeReq": {
    "collectionMethod": "camera",
    "lensType": "ortho",
    "overlapH": 80,
    "overlapW": 70,
    "elevationOptimizeEnable": 1,
    "shootType": "time",
    "direction": 89,
    "margin": 0,
    "coordinates": [
      {
        "longitude": 113.940334790292,
        "latitude": 22.5798256945836,
        "height": 0
      },
      {
        "longitude": 113.943015351291,
        "latitude": 22.5798490554135,
        "height": 0
      },
      {
        "longitude": 113.942911397786,
        "latitude": 22.5816299613237,
        "height": 0
      },
      {
        "longitude": 113.940344598963,
        "latitude": 22.5816387784883,
        "height": 0
      }
    ]
  },
  "startActionList": [
    {
      "actionIndex": 0,
      "gimbalYawRotateAngle": -90
    },
    {
      "actionIndex": 1,
      "hoverTime": 0.5
    }
  ],
  "routePointList": [
    {
      "routePointIndex": 0,
      "longitude": 113.940343144377,
      "latitude": 22.5813699888658,
      "timeInterval": 2,
      "endIntervalRouteIndex": 0,
      "actions": [
        {
          "actionIndex": 0,
          "takePhotoType": 0,
          "useGlobalImageFormat": 1
        }
      ]
    },
    {
      "routePointIndex": 1,
      "longitude": 113.942924142576,
      "latitude": 22.5814115656784
    },
    {
      "routePointIndex": 2,
      "longitude": 113.942943025065,
      "latitude": 22.5810880647388
    },
    {
      "routePointIndex": 3,
      "longitude": 113.940341392538,
      "latitude": 22.5810461555712
    },
    {
      "routePointIndex": 4,
      "longitude": 113.940339640707,
      "latitude": 22.5807223222765
    },
    {
      "routePointIndex": 5,
      "longitude": 113.942961907465,
      "latitude": 22.5807645637969
    },
    {
      "routePointIndex": 6,
      "longitude": 113.942980789776,
      "latitude": 22.5804410628529
    },
    {
      "routePointIndex": 7,
      "longitude": 113.940337888885,
      "latitude": 22.5803984889819
    },
    {
      "routePointIndex": 8,
      "longitude": 113.940336137071,
      "latitude": 22.5800746556873
    },
    {
      "routePointIndex": 9,
      "longitude": 113.942997724328,
      "latitude": 22.5801175305482
    },
    {
      "routePointIndex": 10,
      "longitude": 113.942999671999,
      "latitude": 22.5801175619067
    },
    {
      "routePointIndex": 11,
      "longitude": 113.941647829558,
      "latitude": 22.580729324342
    }
  ]
}
```
#### 1.3 倾斜摄影 KMZ 文件 请求参数json
```json
{
  "templateType": "mapping3d",
  "takeOffRefPoint": "22.583353,113.935224,12.194280",
  "droneType": 91,
  "subDroneType": 1,
  "payloadType": 81,
  "payloadPosition": 0,
  "imageFormat": "visable",
  "finishAction": "goHome",
  "exitOnRcLostAction": "goBack",
  "globalHeight": 100,
  "autoFlightSpeed": 10,
  "waypointHeadingReq": {
    "waypointHeadingMode": "followWayline"
  },
  "waypointTurnReq": {
    "waypointTurnMode": "toPointAndStopWithDiscontinuityCurvature"
  },
  "gimbalPitchMode": "usePointSetting",
  "mappingTypeReq": {
    "collectionMethod": "camera",
    "lensType": "ortho",
    "overlapH": 80,
    "overlapW": 70,
    "elevationOptimizeEnable": 1,
    "shootType": "time",
    "direction": 89,
    "margin": 0,
    "inclinedGimbalPitch":"-40",
    "inclinedFlightSpeed":"12",
    "coordinates": [
      {
        "longitude": 113.940334790292,
        "latitude": 22.5798256945836,
        "height": 0
      },
      {
        "longitude": 113.943015351291,
        "latitude": 22.5798490554135,
        "height": 0
      },
      {
        "longitude": 113.942911397786,
        "latitude": 22.5816299613237,
        "height": 0
      },
      {
        "longitude": 113.940344598963,
        "latitude": 22.5816387784883,
        "height": 0
      }
    ]
  },
  "folderList":[
    {
        "wayLineId":"0",
        "startActionList": [
            {
            "actionIndex": 0,
            "gimbalYawRotateAngle": -90
            },
            {
            "actionIndex": 1,
            "hoverTime": 0.5
            }
        ],
        "routePointList": [
            {
            "routePointIndex": 0,
            "longitude": 113.940343144377,
            "latitude": 22.5813699888658,
            "timeInterval": 2,
            "endIntervalRouteIndex": 0,
            "actions": [
                {
                "actionIndex": 0,
                "takePhotoType": 0,
                "useGlobalImageFormat": 1
                }
            ]
            },
            {
            "routePointIndex": 1,
            "longitude": 113.942924142576,
            "latitude": 22.5814115656784
            },
            {
            "routePointIndex": 2,
            "longitude": 113.942943025065,
            "latitude": 22.5810880647388
            }
        ]
    },
    {
        "wayLineId":"1",
        "routePointList": [
            {
            "routePointIndex": 0,
            "longitude": 113.940343144377,
            "latitude": 22.5813699888658,
            "timeInterval": 2,
            "endIntervalRouteIndex": 0,
            "actions": [
                {
                "actionIndex": 0,
                "takePhotoType": 0,
                "useGlobalImageFormat": 1
                }
            ]
            },
            {
            "routePointIndex": 1,
            "longitude": 113.942924142576,
            "latitude": 22.5814115656784
            },
            {
            "routePointIndex": 2,
            "longitude": 113.942943025065,
            "latitude": 22.5810880647388
            }
        ]
    },
    {
        "wayLineId":"2",
        "routePointList": [
            {
            "routePointIndex": 0,
            "longitude": 113.940343144377,
            "latitude": 22.5813699888658,
            "timeInterval": 2,
            "endIntervalRouteIndex": 0,
            "actions": [
                {
                "actionIndex": 0,
                "takePhotoType": 0,
                "useGlobalImageFormat": 1
                }
            ]
            },
            {
            "routePointIndex": 1,
            "longitude": 113.942924142576,
            "latitude": 22.5814115656784
            },
            {
            "routePointIndex": 2,
            "longitude": 113.942943025065,
            "latitude": 22.5810880647388
            }
        ]
    },
    {
        "wayLineId":"3",
        "routePointList": [
            {
            "routePointIndex": 0,
            "longitude": 113.940343144377,
            "latitude": 22.5813699888658,
            "timeInterval": 2,
            "endIntervalRouteIndex": 0,
            "actions": [
                {
                "actionIndex": 0,
                "takePhotoType": 0,
                "useGlobalImageFormat": 1
                }
            ]
            },
            {
            "routePointIndex": 1,
            "longitude": 113.942924142576,
            "latitude": 22.5814115656784
            },
            {
            "routePointIndex": 2,
            "longitude": 113.942943025065,
            "latitude": 22.5810880647388
            }
        ]
    },
    {
        "wayLineId":"4",
        "routePointList": [
            {
            "routePointIndex": 0,
            "longitude": 113.940343144377,
            "latitude": 22.5813699888658,
            "timeInterval": 2,
            "endIntervalRouteIndex": 0,
            "actions": [
                {
                "actionIndex": 0,
                "takePhotoType": 0,
                "useGlobalImageFormat": 1
                }
            ]
            },
            {
            "routePointIndex": 1,
            "longitude": 113.942924142576,
            "latitude": 22.5814115656784
            },
            {
            "routePointIndex": 2,
            "longitude": 113.942943025065,
            "latitude": 22.5810880647388
            }
        ]
    }
  ]
}
```


