package app.okuyama.yuu.test_keiziban

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import app.okuyama.yuu.test_keiziban.databinding.ActivitySelectBinding
import app.okuyama.yuu.test_keiziban.databinding.ThreadNameBinding

class SelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
        binding = ActivitySelectBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val AddIntent:Intent = Intent(this,AddActivity::class.java)

        binding.tuika.setOnClickListener {
            startActivity(AddIntent)
        }
    }
}