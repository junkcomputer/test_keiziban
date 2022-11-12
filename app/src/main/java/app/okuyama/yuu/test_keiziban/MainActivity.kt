package app.okuyama.yuu.test_keiziban

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.UserDictionary.Words
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import app.okuyama.yuu.test_keiziban.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val db = Firebase.firestore

//送信ボタン
        binding.sousin.setOnClickListener {

            val data = Datas(
                name = binding.onamae.text.toString(),
                text = binding.kakiko.text.toString()
            )

            //書き込み
            db.collection("Test")
                .add(data)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

            val MainIntent: Intent = Intent(this,MainActivity::class.java)
            startActivity(MainIntent)
        }

        //読み込み
        db.collection("Test")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val layout = LinearLayout(this)
                    val nameTextView = TextView(this)
                    val textTextView = TextView(this)
                    val idTextView = TextView(this)

                    idTextView.text = document.id
                    nameTextView.text = document.data.get("name").toString()
                    textTextView.text = document.data.get("text").toString()

                    nameTextView.textSize = 10.0f
                    textTextView.textSize = 15.0f
                    idTextView.textSize = 5.0f

                    layout.addView(idTextView)
                    layout.addView(nameTextView)
                    layout.addView(textTextView)

                    val lp = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    lp.gravity = Gravity.CENTER_VERTICAL
                    nameTextView.layoutParams = lp
                    textTextView.layoutParams = lp
                    idTextView.layoutParams = lp

                    binding.yomikomikun.addView(layout)
                }
            }

            .addOnFailureListener { e ->
                Log.w(TAG,"Error reading document",e)
            }

        binding.kousinkun.setOnClickListener {
            val MainIntent: Intent = Intent(this,MainActivity::class.java)
            startActivity(MainIntent)
        }
    }
}