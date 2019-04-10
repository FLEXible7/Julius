package com.example.julius

class DealInfo{
    var deal: String = ""
    var tag: String = ""
    var date: String = ""

    constructor(){}

    constructor(deal: String, tag: String, date: String){
        this.deal = deal
        this.tag = tag
        this.date = date
    }
}