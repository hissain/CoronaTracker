//
//  RestRequestService.swift
//  CoronaTracker
//
//  Created by Hissain on 1/4/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import Foundation
import CoreLocation

protocol RestRequest {

    func addUser(userInfo: CTUserInfo, onResult: @escaping (Bool) -> Void)
    func addEvent(location: CLLocation)
    func fetchCandidates(queryInfo: CTQueryInfo, onResult: @escaping ([CTUserInfo]?) -> Void)
}

class RestRequestService: RestRequest {

    func addUser(userInfo: CTUserInfo, onResult: @escaping (Bool) -> Void) {

        RestApiService().registerUser(userInfo: userInfo) { (result) in
            if let user = result, let id = user.id {
                print("Success sending to server, user: \(user)")
                UserStoreData.userID = String(id)
                onResult(true)
            }else {
                print("Failure sending userInfo to server")
                onResult(false)
            }
        }
    }

    func addEvent(location: CLLocation) {

        let event = CTEventInfo()
        event.altitude = location.altitude
        event.latitude = location.coordinate.latitude
        event.longitude = location.coordinate.longitude
        event.userId = Int(UserStoreData.userID)

        RestApiService().registerEvent(eventInfo: event) { (result) in
            if let event = result, let id = event.eventId {
                print("Success sending to server, event id: \(id)")
            }else {
                print("Filure sending event to server")
            }
        }
    }

    /** BELOW IS A RESTRICTED API CALL, WONT BE AVAILABLE TO CLIENT. THIS IS JUST FOR TESTING */

    func fetchCandidates(queryInfo: CTQueryInfo, onResult: @escaping ([CTUserInfo]?) -> Void) {

        RestApiService().fetchCandidates(userInfo: queryInfo) { (result) in

            if let users = result {
                print("Success retrieving \(users.count) canidates from server")
                onResult(users)
            }else {
                print("Failure retrieving canidates from server")
                onResult(nil)
            }
        }
    }
}
