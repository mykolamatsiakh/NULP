//
//  ViewController.swift
//  PassingData
//
//  Created by Ivan Sapiga on 15.10.2017.
//  Copyright © 2017 Ivan Sapiga. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
   
    @IBOutlet var theTextField: UITextField!
    
    var LabelText = String()
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationController?.navigationBar.setBackgroundImage(UIImage(), for: .default)    //set background image for
                                                                                                //tab Bar (transparent)
        navigationController?.navigationBar.shadowImage = UIImage()                         //delate line under bar
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let DestViewController : ViewTwo = segue.destination as! ViewTwo                    //set path
        DestViewController.LabelText = theTextField.text!                               // sand text to another controller
    }

}

