//
//  CTEvent.swift
//  CoronaTracker
//
//  Created by Hissain on 29/3/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import Foundation

class CTEventInfo: Codable {

    var eventId: Int?
    var userId: Int?
    var latitude: Double?
    var longitude: Double?
    var altitude: Double?

    private enum CodingKeys : String, CodingKey {
        case userId, eventId, latitude, longitude, altitude
    }

    init() {}
    init(userId: Int, lat: Double, lon: Double, alt: Double) {
        self.userId = userId
        self.latitude = lat
        self.longitude = lon
        self.altitude = alt
    }
    init(eventId: Int, userId: Int, lat: Double, lon: Double, alt: Double) {
        self.eventId = eventId
        self.userId = userId
        self.latitude = lat
        self.longitude = lon
        self.altitude = alt
    }
}
