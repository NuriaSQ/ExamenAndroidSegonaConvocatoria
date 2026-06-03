package cat.itic.examensegonaconvocatoria

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cat.itic.examensegonaconvocatoria.fragments.LlibresFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<LlibresFragment>(R.id.fragmentContainer)
       }

        val navMenu = findViewById<BottomNavigationView>(R.id.bottomNav)

        val text = "Prova"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(this, text, duration)

        navMenu.setOnClickListener {
            toast.show()
        }
    }
}