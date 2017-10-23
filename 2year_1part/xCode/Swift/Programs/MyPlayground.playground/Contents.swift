//: Playground - noun: a place where people can play

import Cocoa
let kek = 5
let myString:String=("lol")
let anotherString=(" kek")
var allStrings=(myString+anotherString+" hebyrek " + kek.description)
print(allStrings)


let arr=["lol","kek","4ebyrek"]
for street in arr {
    print("I don't live on \(street).", separator: " kek")
}
print(kek,myString,separator:" kek ")


var students=["Bob"]
students.append("Karl")
students.append(contentsOf: ["Michal","Jesy"])
print(students.count)
students.insert("Geri", at: 2)

