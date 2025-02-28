package dev.androidbroadcast.contactapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ListView

class ContactsListViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.list_view_fragment, container, false)
        val listView = view.findViewById<ListView>(R.id.contact_list_view)

        val contacts = ContactHelper.getContacts(requireContext())
        val adapter = ListViewAdapter(requireContext(), contacts)
        listView.adapter = adapter

        return view
    }
}

