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
            if result {
                print("Success sending to server, user: \(userInfo)")
                UserStoreData.registered = RegistrationStatus.Registered.rawValue
            }else {
                print("Failure sending to server, user: \(userInfo)")
            }
        }
    }


    func addEvent(location: CLLocation) {

        let apiService = RestApiService()
        let event = CTEventInfo()
        event.altitude = location.altitude
        event.latitude = location.coordinate.latitude
        event.longitude = location.coordinate.longitude
        event.nationalID = UserStoreData.nationalID
        event.deviceUUID = UserStoreData.deviceID
        event.number = UserStoreData.number

        apiService.registerEvent(eventInfo: event) { (result) in
            if result {
                print("Success sending to server, location: \(location)")
            }else {
                print("Filure sending to server, location: \(location)")
            }
        }
    }
}

