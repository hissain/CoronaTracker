package bd.ctracker.com.ctracker.model


data class CTLocation(val latitude: Float,
                      val longitude: Float,
                      val altitude: Float)

data class CTEventInfo(val phoneNumber: String,
                       val nationalID: String,
                       val deviceUUID: String,
                       val location: CTLocation
)