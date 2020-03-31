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

        var request = URLRequest(url: URL.init(string: "http://localhost:8080/users")!)
        request.httpMethod = HTTPMethod.post.rawValue
        request.setValue("application/json; charset=UTF-8", forHTTPHeaderField: "Content-Type")

        let jsonEncoder = JSONEncoder()
        do {
            let jsonData = try jsonEncoder.encode(userInfo)
            let jsonString = String(data: jsonData, encoding: .utf8)
            print("Request body: \(String(describing: jsonString))")
            request.httpBody = jsonData
        }
        catch {
            print(error.localizedDescription)
        }


        AF.request(request)
            .validate()
            .responseData { (response) in
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

        var request = URLRequest(url: URL.init(string: "http://localhost:8080/events")!)
        request.httpMethod = HTTPMethod.post.rawValue
        request.setValue("application/json; charset=UTF-8", forHTTPHeaderField: "Content-Type")

        let jsonEncoder = JSONEncoder()
        do {
            let jsonData = try jsonEncoder.encode(eventInfo)
            let jsonString = String(data: jsonData, encoding: .utf8)
            print("Request body: \(String(describing: jsonString))")
            request.httpBody = jsonData
        }
        catch {
            print(error.localizedDescription)
        }

        AF.request(request)
            .validate()
            .responseData { (response) in
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
