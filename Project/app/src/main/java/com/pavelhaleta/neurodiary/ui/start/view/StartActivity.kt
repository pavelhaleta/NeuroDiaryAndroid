package com.pavelhaleta.neurodiary.ui.start.view


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.pavelhaleta.neurodiary.R
import com.pavelhaleta.neurodiary.ui.main.view.MainMenuActivity
import com.pavelhaleta.neurodiary.ui.start.viewmodel.StartVM

class StartActivity : AppCompatActivity(), StartVM.VMListener {

    lateinit var viewModel: StartVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        viewModel = StartVM(this, this)
        if (viewModel.isAccountExists()) {
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
    override fun changeToSignIn() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SignInFragment>(R.id.fragment_container_view)
        }
    }
}
