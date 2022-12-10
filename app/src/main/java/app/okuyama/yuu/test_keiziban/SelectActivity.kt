package app.okuyama.yuu.test_keiziban

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import app.okuyama.yuu.test_keiziban.databinding.ActivitySelectBinding
import app.okuyama.yuu.test_keiziban.databinding.ThreadNameBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
        binding = ActivitySelectBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val db = Firebase.firestore

        val adapter = ThreadNameAdapter()
        db.collection("Thread")
            .get()
            .addOnSuccessListener { result ->
                val datas  = mutableListOf<Datas>()
                for (document in result) {
                    datas.add(Datas(document.data.get("name").toString(),document.data.get("text").toString()))
                }
                adapter.updateThreads(datas)
            }

        binding.recyclerView.adapter = adapter

        val AddIntent:Intent = Intent(this,AddActivity::class.java)

        binding.tuika.setOnClickListener {
            startActivity(AddIntent)
        }
    }
}