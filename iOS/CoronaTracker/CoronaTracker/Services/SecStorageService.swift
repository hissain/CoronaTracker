//
//  SecStorageService.swift
//  CoronaTracker
//
//  Created by Hissain on 29/3/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import Foundation

@propertyWrapper
struct EncryptedStringStorage {

    private let key: String

    init(key: String) {
        self.key = key
    }

    var wrappedValue: String {
        get {
            // Get encrypted string from UserDefaults
            return UserDefaults.standard.string(forKey: key) ?? ""
        }
        set {
            // Encrypt newValue before set to UserDefaults
            let encrypted = encrypt(value: newValue)
            UserDefaults.standard.set(encrypted, forKey: key)
        }
    }

    private func encrypt(value: String) -> String {
        // Encryption logic here
        return String(value.reversed())
    }
}
