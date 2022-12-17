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
    private lateinit var Addbinding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        Addbinding = ActivityAddBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        var db = Firebase.firestore

        val MainIntent: Intent = Intent(this,MainActivity::class.java)

        Addbinding.sousinTuikaKun.setOnClickListener {

            val data = Datas(
                name = Addbinding.onamaeTuikaKun.text.toString(),
                text = Addbinding.honbunTuikaKun.text.toString()
            )

            db.collection("Thread").document(Addbinding.threadTuikaKun.text.toString())
                .set(data)
                .addOnSuccessListener { documentReference ->
                    startActivity(MainIntent)
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }
        }


    }
}