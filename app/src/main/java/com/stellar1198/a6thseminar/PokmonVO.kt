package com.stellar1198.a6thseminar

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PokmonVO : RealmObject() {
    @PrimaryKey
    var name : String = ""
    var num : Int = 0
    var type : String = ""
    var user : String = ""
}