package com.stellar1198.a6thseminar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var memberRealm : Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()

        login_check_btn.setOnClickListener {
            var id = login_id_edit.text.toString()
            var pwd = login_pw_edit.text.toString()

            if (getMemberList(id).isEmpty()){
                Toast.makeText(this, "존재하는 아이디가 없습니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                if (login_auto_check.isChecked){
                    SharedPreferenceController.setID(this, id)
                    SharedPreferenceController.setPWD(this, pwd)
                }
                var intent = Intent(this, MainActivity6::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
                finish()
            }
        }

        login_sign_btn.setOnClickListener {
            startActivity(Intent(this, SignActivity::class.java))
        }
    }

    fun init(){
        Realm.init(this)
        memberRealm = Realm.getDefaultInstance()
    }

    fun getMemberList(id : String) : RealmResults<MemberVO> {
        return memberRealm.where(MemberVO::class.java).equalTo("id", id).findAll()
    }
}
