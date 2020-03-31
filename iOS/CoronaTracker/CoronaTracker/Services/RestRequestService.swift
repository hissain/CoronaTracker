//
//  RestRequestService.swift
//  CoronaTracker
//
//  Created by Hissain on 1/4/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import Foundation
import CoreLocation

class RestRequestService {

    func addUser(userInfo: CTUserInfo) {
        let apiService = RestApiService()
        apiService.registerUser(userInfo: userInfo) { (result) in
            if let user = result, let id = user.id {
                print("Success sending to server, user: \(user)")
                UserStoreData.userID = String(id)
            }else {
                print("Failure sending userInfo to server")
            }
        }
    }


    func addEvent(location: CLLocation) {

        let apiService = RestApiService()
        let event = CTEventInfo()
        event.altitude = location.altitude
        event.latitude = location.coordinate.latitude
        event.longitude = location.coordinate.longitude
        event.userId = Int(UserStoreData.userID)

        apiService.registerEvent(eventInfo: event) { (result) in
            if let event = result, let id = event.eventId {
                print("Success sending to server, event id: \(id)")
            }else {
                print("Filure sending event to server")
            }
        }
    }
}
