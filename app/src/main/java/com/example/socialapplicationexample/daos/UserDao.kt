package com.example.socialapplicationexample.daos

import com.example.socialapplicationexample.models.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {
    //Creation of Firebase Database Instance
    private val db =  FirebaseFirestore.getInstance()
    //Adding the collection of Firebase.
    private val usersCollection = db.collection("users")

    fun addUser(user: User?)
    {
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                usersCollection.document(user.uId).set(it)

            }

        }
    }

}