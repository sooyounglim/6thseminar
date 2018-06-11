package com.stellar1198.a6thseminar

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MemberVO : RealmObject() {
    @PrimaryKey
    var id : String = ""
    var pwd : String = ""
}