package com.example.blocnotas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.blocnotas.adapter.NotesAdapter
import com.example.blocnotas.data.notasPrueba
import com.example.blocnotas.databinding.ActivityMainBinding
import com.example.blocnotas.databinding.FragmentNoteListBinding
import javax.sql.DataSource

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //Configuración de la flecha de la toolbar para que vuelva hacia atrás
        binding.toolbar.setNavigationOnClickListener{navController.popBackStack()}

    }
}

/* override fun onCreateOptionsMenu(menu: Menu): Boolean {
     // Inflate the menu; this adds items to the action bar if it is present.
     enuInflater.inflate(R.menu.menu_main, menu)
     return true
 }

 override fun onOptionsItemSelected(item: MenuItem): Boolean {
     // Handle action bar item clicks here. The action bar will
     // automatically handle clicks on the Home/Up button, so long
     // as you specify a parent activity in AndroidManifest.xml.
     return when (item.itemId) {
         R.id.action_settings -> true
         else -> super.onOptionsItemSelected(item)
     }
 }

 override fun onSupportNavigateUp(): Boolean {
     val navController = findNavController(R.id.nav_host_fragment_content_main)
     return navController.navigateUp(appBarConfiguration)
             || super.onSupportNavigateUp()
 }
} */
