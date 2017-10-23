//
//  ViewTwo.swift
//  PassingData
//
//  Created by Ivan Sapiga on 15.10.2017.
//  Copyright © 2017 Ivan Sapiga. All rights reserved.
//

import Foundation
import UIKit

class ViewTwo: UIViewController {
    
    @IBOutlet var theLabel: UILabel!
    @IBOutlet var setTitle: UINavigationBar!
    
    var LabelText = String();
    

    override func viewDidLoad() {
        theLabel.text = LabelText
        
   
    }
}
