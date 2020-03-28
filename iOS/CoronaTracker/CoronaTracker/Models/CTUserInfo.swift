//
//  CTUserInfo.swift
//  CoronaTracker
//
//  Created by Hissain on 29/3/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import Foundation

class CTUserInfo {

    var name: String?
    var phoneNumber: String?
    var nationalID: String?
    var deviceUUID: String?

    init() {}
    init(name: String, phoneNumber: String, nationalID: String, deviceUUID: String){
        self.name = name
        self.phoneNumber = phoneNumber
        self.nationalID = nationalID
        self.deviceUUID = deviceUUID
    }
}
