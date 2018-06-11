package com.stellar1198.a6thseminar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({
            if (SharedPreferenceController.getID(this) == "") {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            else {
                val intent = Intent(this, MainActivity6::class.java)
                intent.putExtra("id", SharedPreferenceController.getID(this))
                startActivity(intent)
                finish()
            }
        }, 2000)
    }
}
