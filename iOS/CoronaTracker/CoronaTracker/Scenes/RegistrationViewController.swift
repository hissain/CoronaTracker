//
//  ViewController.swift
//  CoronaTracker
//
//  Created by Hissain on 28/3/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import UIKit
import CoreLocation

class RegistrationViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var tfName: UITextField!
    @IBOutlet weak var tfNumber: UITextField!
    @IBOutlet weak var tfNationalID: UITextField!
    @IBOutlet weak var btnRegister: UIButton!
    @IBOutlet weak var btnProblem: UIButton!

    let restApiService = RestApiService()
    let locationService = LocationService()

    override func viewDidLoad() {
        super.viewDidLoad()

        self.title = "Complete Registration"
        self.registerObservers()

        self.btnRegister.addTarget(self, action: #selector(signup(button:)), for: UIControl.Event.touchUpInside)
        self.locationService.requestPermission()
    }

    func registerObservers(){

        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillAppear(notification:)), name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillHide(notification:)), name: UIResponder.keyboardWillHideNotification, object: nil)
    }

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }

    @objc func signup(button: UIButton){

        guard let name = tfName.text, !name.isEmpty else { return }
        guard let number = tfNumber.text, !number.isEmpty else { return }
        guard let nationalID = tfNationalID.text, !nationalID.isEmpty else { return }
        let deviceId = UUID.init().uuidString

        print("Proceed with name: \(name), number: \(number) and ID: \(nationalID)")

        // Saving numbers to Preference/ User default

        UserStoreData.name = name
        UserStoreData.number = number
        UserStoreData.nationalID = nationalID
        UserStoreData.deviceID = deviceId

        let userInfo = CTUserInfo(name: name, phoneNumber: number, nationalID: nationalID, deviceUUID: deviceId)
        RestRequestService().addUser(userInfo: userInfo)

        // Test Code
//        let apiService = RestApiService()
//        let event = CTEventInfo()
//        event.altitude = 10.101
//        event.latitude = 97.202
//        event.longitude = -79.305
//        event.userId = 1004
//
//        apiService.registerEvent(eventInfo: event) { (result) in
//            if let e = result, let id = e.eventId {
//                print("Success sending to server, event id: \(id)")
//            }else {
//                print("Filure sending to server, event")
//            }
//        }
        
    }

    @objc func keyboardWillAppear(notification: Notification){
        if let keyboardFrame: NSValue = notification.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? NSValue {
            let keyboardRectangle = keyboardFrame.cgRectValue
            let keyboardHeight = keyboardRectangle.height
            self.view.transform = CGAffineTransform(translationX: 0, y: -keyboardHeight/2)
        }
    }

    @objc func keyboardWillHide(notification: Notification){
        self.view.transform = .identity
    }
}

