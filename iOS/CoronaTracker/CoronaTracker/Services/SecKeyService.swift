//
//  SecKeyService.swift
//  CoronaTracker
//
//  Created by Hissain on 29/3/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import Foundation

struct UserStoreData {
    @EncryptedStringStorage(key: "kUserName")
    static var name: String

    @EncryptedStringStorage(key: "kUserPhoneNumber")
    static var number: String

    @EncryptedStringStorage(key: "kUserNationalID")
    static var nationalID: String

    @EncryptedStringStorage(key: "kUserDeviceID")
    static var deviceID: String

    @EncryptedStringStorage(key: "kUserRegistered")
    static var registered: String
}
