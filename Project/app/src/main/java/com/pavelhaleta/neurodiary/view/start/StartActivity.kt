package com.pavelhaleta.neurodiary.view.start


import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.database.DBHelper
import com.pavelhaleta.neurodiary.database.tables.User
import com.pavelhaleta.neurodiary.view.main.MainMenuActivity
import com.pavelhaleta.neurodiary.viewmodel.listeners.StartActivityVMListener

class StartActivity : AppCompatActivity(), StartActivityVMListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        if (isAccountExists()) {
            //add sign in fragment
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<SignInFragment>(R.id.fragment_container_view)
            }
        } else {
            //add sign up fragment
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<SignUpFragment>(R.id.fragment_container_view)
            }
        }
    }

    override fun onStart() {
        super.onStart()
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

    private fun isAccountExists(): Boolean {
        val dbHelper = DBHelper(this)
        val list = User.toList(dbHelper.db, "")
        dbHelper.close()
        return !list.isNullOrEmpty()
    }

    override fun enterUser() {
        startActivity(Intent(this, MainMenuActivity::class.java))
        finish()
    }

    override fun changeToSignUp() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SignUpFragment>(R.id.fragment_container_view)
        }
    }
}
