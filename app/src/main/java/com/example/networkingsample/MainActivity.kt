package com.example.networkingsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val users = arrayListOf<String>()
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)

        val listView = findViewById<ListView>(R.id.userlist)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        listView.adapter = arrayAdapter

        val todosApi = RetrofitHelper.getInstance().create(TodosApi::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            val result = todosApi.getTodos()
            if (result.isSuccessful) {
                progressBar.visibility = View.GONE
                arrayAdapter.clear()
                result.body()?.map { it.title }?.toCollection(users)
            } else {
                progressBar.visibility = View.GONE
                arrayAdapter.add("No Data")
            }
        }
//        Important: resource will not be really updated on the server but it will be faked as if.
    }
}