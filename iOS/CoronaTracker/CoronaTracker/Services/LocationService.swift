//
//  LocationService.swift
//  CoronaTracker
//
//  Created by Hissain on 31/3/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import Foundation
import CoreLocation

class LocationService: NSObject, CLLocationManagerDelegate {

    var locationManager: CLLocationManager?

    override init() {
        super.init()

        locationManager = CLLocationManager()
        locationManager?.allowsBackgroundLocationUpdates = true
        locationManager?.delegate = self
    }

    func requestPermission(){
        locationManager?.requestWhenInUseAuthorization()
    }

    func startLocationService() {
        locationManager?.startUpdatingLocation()
    }

    func stopLocationService() {
        locationManager?.stopUpdatingLocation()
    }

    func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
        if status == .authorizedAlways {
            startLocationService()
        } else if status == .authorizedWhenInUse {
            startLocationService()
        } else if status == .denied {
            print("Location authorization denied!")
        } else if status == .restricted {
            print("Location authorization restricted!")
        }else if status == .notDetermined{
            print("Location authorization not determined!")
        }
    }

    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {

        if let location = locations.last {
            print("New location is \(location)")

            let apiService = RestApiService()
            let event = CTEventInfo()
            event.location = CTLocation()
            event.location?.altitude = location.altitude
            event.location?.latitude = location.coordinate.latitude
            event.location?.longitude = location.coordinate.longitude
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
}


