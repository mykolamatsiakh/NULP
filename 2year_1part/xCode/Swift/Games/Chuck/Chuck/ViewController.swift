//
//  ViewController.swift
//  Chuck
//
//  Created by Ivan Sapiga on 12.10.2017.
//  Copyright © 2017 Ivan Sapiga. All rights reserved.
//

import UIKit

func getRandomJoke(block: (_ textJoke: String)->Void) {
    //http://api.icndb.com/jokes/random
    
    if let urlRandomJoke = URL(string: "http://api.icndb.com/jokes/random") {
        if let dataJson = try? Data(contentsOf: urlRandomJoke) {
            
            if let jsonDict = try? JSONSerialization.jsonObject(with: dataJson, options: JSONSerialization.ReadingOptions.allowFragments) {
                
                if let joke = ((jsonDict as! NSDictionary).object(forKey: "value") as! NSDictionary).object(forKey: "joke") as? String {
                    
                    block(joke.replacingOccurrences(of: "&quot", with: "\""))
                    return
                }


            }
        }
    }

    block("")

};

class ViewController: UIViewController {

    @IBOutlet weak var labelJoke: UILabel!
    
    @IBAction func pushShareButtonAction(_ sender: Any) {
        let activityController = UIActivityViewController(activityItems: [labelJoke.text!], applicationActivities: nil)
        present(activityController, animated: true, completion: nil)
    }
    @IBAction func pushRefreshButtonAction(_ sender: Any) {
        getRandomJoke { (jokeText) in
            self.labelJoke.text = jokeText
        }
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

