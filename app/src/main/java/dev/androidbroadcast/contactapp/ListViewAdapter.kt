package dev.androidbroadcast.contactapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class ListViewAdapter(private val context: Context, private val contacts: List<Contact>) :
    android.widget.BaseAdapter() {

    override fun getCount(): Int = contacts.size
    override fun getItem(position: Int): Any = contacts[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)
        val contact = contacts[position]

        val nameText = view.findViewById<TextView>(R.id.contact_name)
        val phoneText = view.findViewById<TextView>(R.id.contact_number)
        val imageView = view.findViewById<ImageView>(R.id.contact_image)

        nameText.text = contact.name
        phoneText.text = contact.phoneNumber

        if (contact.photoUri != null) {
            imageView.setImageURI(Uri.parse(contact.photoUri))
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.default_avatar))
        }

        view.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${contact.phoneNumber}"))
            context.startActivity(intent)
        }

        return view
    }
}
