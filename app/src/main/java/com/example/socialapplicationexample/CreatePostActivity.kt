package com.example.socialapplicationexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.example.socialapplicationexample.daos.PostDao

class CreatePostActivity : AppCompatActivity() {
    lateinit var postDao: PostDao
    lateinit var edtPost:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        setIds()

    }

    private fun setIds(){
        edtPost = findViewById<EditText>(R.id.postEdittext)
    }

    fun postOnclick(view: View) {
        val input = edtPost.text.toString().trim()
        if (input.isNotEmpty())
        {
            postDao = PostDao()
            postDao.addPost(input)
            finish()
        }
    }

}