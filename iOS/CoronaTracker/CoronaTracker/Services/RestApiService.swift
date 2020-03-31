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

    func registerUser(userInfo: CTUserInfo, completionBlock: @escaping (CTUserInfo?) -> Void ){

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
            .responseJSON { (response) in
                switch response.result {
                case .success( _):
                    var addedUser: CTUserInfo? = nil
                    if let data = response.data {
                        addedUser = try? JSONDecoder().decode(CTUserInfo.self, from: data)
                    }
                    completionBlock(addedUser)
                case .failure(let error):
                    print("Error: \(error)")
                    completionBlock(nil)
                }
        }
    }


    func registerEvent(eventInfo: CTEventInfo, completionBlock: @escaping (CTEventInfo?) -> Void ){

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
            .responseJSON { (response) in
                switch response.result {
                case .success( _):
                    var addedEvent: CTEventInfo? = nil
                    if let data = response.data {
                        addedEvent = try? JSONDecoder().decode(CTEventInfo.self, from: data)
                    }
                    completionBlock(addedEvent)
                case .failure(let error):
                    print("Error: \(error)")
                    completionBlock(nil)
                }
        }
    }
}
