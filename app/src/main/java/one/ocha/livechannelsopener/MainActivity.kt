package one.ocha.livechannelsopener

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            startActivity(packageManager.getLaunchIntentForPackage("com.google.android.tv"))
        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.missing_app), Toast.LENGTH_SHORT).show()
            //https://stackoverflow.com/questions/7331793/using-a-string-resource-in-a-toast
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.tv")))
                //https://blog.shg25.com/?p=19
            } catch (anfe: android.content.ActivityNotFoundException) {
                Toast.makeText(this, getString(R.string.missing_app), Toast.LENGTH_LONG).show();
            }
        }

        val exit_button = findViewById<Button>(R.id.exit_button)
        exit_button.setOnClickListener{
            android.os.Process.killProcess(android.os.Process.myPid());
            //https://www.ecoop.net/memo/archives/2011-01-24-1.html
        }
    }
}