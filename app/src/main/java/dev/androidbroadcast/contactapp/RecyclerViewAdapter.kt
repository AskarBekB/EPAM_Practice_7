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
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val context: Context, private val contacts: List<Contact>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.contact_name)
        val phoneText: TextView = view.findViewById(R.id.contact_number)
        val imageView: ImageView = view.findViewById(R.id.contact_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameText.text = contact.name
        holder.phoneText.text = contact.phoneNumber

        if (contact.photoUri != null) {
            holder.imageView.setImageURI(Uri.parse(contact.photoUri))
        } else {
            holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.default_avatar))
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${contact.phoneNumber}"))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = contacts.size
}
