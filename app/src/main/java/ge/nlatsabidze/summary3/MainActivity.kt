package ge.nlatsabidze.summary3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var infoAdapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        infoAdapter = DataAdapter(mutableListOf())
        val recyclerView = findViewById<RecyclerView>(R.id.rvInfo)
        recyclerView.adapter = infoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val changeButton = findViewById<Button>(R.id.btnAdd)
        val deleteButton = findViewById<Button>(R.id.btnDelete)

        val infoInput = findViewById<EditText>(R.id.etEnterInfo)

        changeButton.setOnClickListener {
            val info = infoInput.text.toString()
            if (info.isNotEmpty() || checkEmailCorrectness(info)) {
                val data = Data(info, false)
                infoAdapter.addUser(data)
                findViewById<EditText>(R.id.etEnterInfo).text.clear()
            }
        }

        deleteButton.setOnClickListener {
            infoAdapter.deleteUser()
        }
    }

    private fun checkEmailCorrectness(email: String):Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}