//
//  CTQueryInfo.swift
//  CoronaTracker
//
//  Created by Hissain on 15/4/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import Foundation

class CTQueryInfo: Codable {

    var userId: Int?
    var maxContactDistance: Int?
    var maxDateCount: Int?
    var accessToken: String?

    init() {}
    init(userId: Int, maxContactDistance: Int, maxDateCount: Int, accessToken: String){
        self.userId = userId
        self.maxContactDistance = maxContactDistance
        self.maxDateCount = maxDateCount
        self.accessToken = accessToken
    }
}
