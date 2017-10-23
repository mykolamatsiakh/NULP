//
//  ViewController.swift
//  MyFirstCoddingAPP
//
//  Created by Ivan Sapiga on 13.10.2017.
//  Copyright © 2017 Ivan Sapiga. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var theLabel: UILabel!
    
    @IBOutlet weak var theTextField: UITextField!
    @IBAction func buttonAction(_ sender: AnyObject)
    {
        let theTextInTextField = theTextField.text
        theLabel.text = theTextInTextField
    }
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning()
    {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

