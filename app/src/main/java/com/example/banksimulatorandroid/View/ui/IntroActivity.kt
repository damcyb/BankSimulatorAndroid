package com.example.banksimulatorandroid.View.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import co.metalab.asyncawait.async
import com.example.banksimulatorandroid.R

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        async {
            val result = await {
                showIntroScreen()
            }
            moveToLoginActivity()
        }
    }

    private fun showIntroScreen() {

        var progressStatus = 0
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        progressBar.progressDrawable.setColorFilter(Color.argb(100, 77, 66, 43)
            , android.graphics.PorterDuff.Mode.SRC_IN)

        while (progressStatus < 100) {
            progressStatus++
            android.os.SystemClock.sleep(50)
            progressBar.progress = progressStatus
        }
    }

    private fun moveToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            //putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}
