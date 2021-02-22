package com.example.socialapplicationexample.daos

import com.example.socialapplicationexample.models.Post
import com.example.socialapplicationexample.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PostDao {
    val db = FirebaseFirestore.getInstance();
    val postCollection = db.collection("posts");
    val auth = Firebase.auth

    fun addPost(text:String)
    {
        val currentuserId  = auth.currentUser!!.uid
        GlobalScope.launch {
            val userDao = UserDao()
            val user =  userDao.getUserByID(currentuserId).await().toObject(User::class.java)!!

            val currentTime = System.currentTimeMillis()
            val post = Post(text,user, currentTime)
            postCollection.document().set(post)
        }

    }
    fun getPostById(postId: String): Task<DocumentSnapshot> {
        return postCollection.document(postId).get()
    }
    fun updateLikes(postId: String) {
        GlobalScope.launch {
            val currentUserId = auth.currentUser!!.uid
            val post = getPostById(postId).await().toObject(Post::class.java)!!
            val isLiked = post.likeBy.contains(currentUserId)

            if(isLiked) {
                post.likeBy.remove(currentUserId)
            } else {
                post.likeBy.add(currentUserId)
            }
            postCollection.document(postId).set(post)
        }

    }
}