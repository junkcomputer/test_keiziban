package app.okuyama.yuu.test_keiziban

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.okuyama.yuu.test_keiziban.databinding.ActivityAddBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.concurrent.thread

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        binding = ActivityAddBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        var db = Firebase.firestore

        binding.sousinTuikaKun.setOnClickListener {

            val data = Datas(
                name = binding.onamaeTuikaKun.text.toString(),
                text = binding.honbunTuikaKun.text.toString()
            )

            db.collection("Thread").document(binding.threadTuikaKun.text.toString())
                .set(data)
                .addOnSuccessListener { documentReference ->
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }

            val MainIntent: Intent = Intent(this,MainActivity::class.java)
            startActivity(MainIntent)
        }


    }
}