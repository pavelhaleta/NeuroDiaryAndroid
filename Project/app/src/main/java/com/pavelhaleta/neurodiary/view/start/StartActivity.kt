package com.pavelhaleta.neurodiary.view.start


import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.database.DBHelper
import com.pavelhaleta.neurodiary.view.base.MainActivity

class StartActivity : MainActivity(R.layout.activity_start) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        if (checkCurrentAccount()){
            //add sign in fragment
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<SignInFragment>(R.id.fragment_container_view)
            }
        }else{
            //add sign up fragment
        }
    }
    override fun onResume() {
        super.onResume()
    }
    override fun onPause() {
        super.onPause()

    }
    override fun onStop() {
        super.onStop()

    }
    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun checkCurrentAccount(): Boolean{
        val dbHelper = DBHelper(this)

        dbHelper.close()
        return true
    }
}