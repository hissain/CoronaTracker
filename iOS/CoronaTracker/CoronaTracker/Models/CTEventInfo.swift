//
//  CTEvent.swift
//  CoronaTracker
//
//  Created by Hissain on 29/3/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import Foundation

class CTEventInfo: Codable {

    var number: String?
    var nationalID: String?
    var deviceUUID: String?
    var latitude: Double?
    var longitude: Double?
    var altitude: Double?

    private enum CodingKeys : String, CodingKey {
        case number, nationalID, deviceUUID, latitude, longitude, altitude
    }

    init() {}
    init(number: String, nationalID: String, deviceID: String, lat: Double, lon: Double, alt: Double) {
        self.number = number
        self.nationalID = nationalID
        self.deviceUUID = deviceID
        self.latitude = lat
        self.longitude = lon
        self.altitude = alt
    }
}
