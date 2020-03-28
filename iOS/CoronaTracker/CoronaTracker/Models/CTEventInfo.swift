//
//  CTEvent.swift
//  CoronaTracker
//
//  Created by Hissain on 29/3/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import Foundation

class CTEventInfo {

    var number: String?
    var nationalID: String?
    var deviceUUID: String?

    var location: CTLocation?

    init() {}
    init(number: String, nationalID: String, deviceID: String, location: CTLocation) {
        self.number = number
        self.nationalID = nationalID
        self.deviceUUID = deviceID
        self.location = location
    }
}

class CTLocation {
    var latitude: Float?
    var longitude: Float?
    var altitude: Float?

    init() {}
    init(lat: Float, lon: Float, alt: Float){
        self.latitude = lat
        self.longitude = lon
        self.altitude = alt
    }
}
