package com.stellar1198.a6thseminar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {

    lateinit var memberRealm : Realm
    lateinit var memberVO: MemberVO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        init()

        sign_check_btn.setOnClickListener {
            val id = sign_id_edit.text.toString()
            val pwd = sign_pw_edit.text.toString()
            if (getMemberList(id).isEmpty()){
                insertMemberList()
                Toast.makeText(this, "가입완료", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            else{
                Toast.makeText(this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun init(){
        Realm.init(this)
        memberRealm = Realm.getDefaultInstance()
    }

    fun getMemberList(id : String) : RealmResults<MemberVO> {
        return memberRealm.where(MemberVO::class.java).equalTo("id", id).findAll()
    }

    fun insertMemberList(){
        memberVO = MemberVO()
        memberVO.id = sign_id_edit.text.toString()
        memberVO.pwd = sign_pw_edit.text.toString()

        memberRealm.beginTransaction()
        memberRealm.copyToRealm(memberVO)
        memberRealm.commitTransaction()
    }
}
