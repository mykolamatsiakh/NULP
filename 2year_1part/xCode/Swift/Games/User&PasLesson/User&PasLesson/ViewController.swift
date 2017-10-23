//
//  ViewController.swift
//  User&PasLesson
//
//  Created by Ivan Sapiga on 13.10.2017.
//  Copyright © 2017 Ivan Sapiga. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var theUsername: UITextField!
    @IBOutlet weak var thePassword: UITextField!
    @IBOutlet weak var theLabel: UILabel!
    @IBAction func theButtonEntreAcrion(_ sender: Any) {
        let user = "ivan"
        let pas = "ninja112"
        if theUsername.text == user &&
            thePassword.text == pas {
            theLabel.text = "Ваші данні вірні"
            theLabel.backgroundColor = UIColor.green
        }else{
            theLabel.text = "Ващі данні не вірні"
            theLabel.backgroundColor = UIColor.red
            
        }
        thePassword.resignFirstResponder()
        theUsername.resignFirstResponder()
    }
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

