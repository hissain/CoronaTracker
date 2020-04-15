//
//  CoronaTrackerTests.swift
//  CoronaTrackerTests
//
//  Created by Hissain on 28/3/20.
//  Copyright © 2020 Corona Tracker. All rights reserved.
//

import XCTest
@testable import CoronaTracker

class CoronaTrackerTests: XCTestCase {

    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testFetch() {

        let q = CTQueryInfo()
        q.accessToken = "1234"
        q.maxContactDistance = 100
        q.userId = 1
        q.maxDateCount = 200

        RestRequestService().fetchCandidates(queryInfo: q) { (result) in
            print("candidates: \(String(describing: result))")
        }
    }

    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }

}
