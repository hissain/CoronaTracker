//
//  TrackingViewController.swift
//  CoronaTracker
//
//  Created by Hissain on 1/4/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import Foundation
import UIKit

class TrackingViewController: UIViewController {

    let locationService = LocationService()

    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "Tracking"

        self.locationService.requestPermission()
    }
}
