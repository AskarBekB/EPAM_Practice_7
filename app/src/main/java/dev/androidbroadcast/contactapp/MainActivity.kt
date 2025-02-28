package dev.androidbroadcast.contactapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.Manifest
import androidx.compose.runtime.savedinstancestate.savedInstanceState

class MainActivity : AppCompatActivity() {
    private val REQUEST_CONTACTS_PERMISSION = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CONTACTS_PERMISSION)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_list_view -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ContactsListViewFragment())
                        .commit()
                }
                R.id.nav_recycler_view -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ContactsRecyclerViewFragment())
                        .commit()
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CONTACTS_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (supportFragmentManager.findFragmentByTag("ListView") == null) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ContactsRecyclerViewFragment(), "ListView")
                        .commit()
                }
                Toast.makeText(this, "URA Access!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "There's no access to contact! God dammit give me access", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


