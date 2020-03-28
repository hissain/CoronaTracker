//
//  RestService.swift
//  CoronaTracker
//
//  Created by Hissain on 29/3/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import Foundation
import Alamofire

class RestApiService {

    func registerUser(userInfo: CTUserInfo, completionBlock: @escaping (Bool) -> Void ){

        AF.request("http://api.server.com/users")
            .responseJSON { (response) in
                switch response.result {
                case .success(let value):
                    print(value)
                    completionBlock(true)
                case .failure(let error):
                    print("Error: \(error)")
                    completionBlock(false)
                }
        }
    }


    func registerEvent(eventInfo: CTEventInfo, completionBlock: @escaping (Bool) -> Void ){

        AF.request("http://api.server.com/events")
            .responseJSON { (response) in
                switch response.result {
                case .success(let value):
                    print(value)
                    completionBlock(true)
                case .failure(let error):
                    print("Error: \(error)")
                    completionBlock(false)
                }
        }
    }
}
