package dev.androidbroadcast.contactapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


class ContactsRecyclerViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.recycler_view_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.contact_recycler_view)

        val contacts = ContactHelper.getContacts(requireContext())
        recyclerView.adapter = RecyclerViewAdapter(requireContext(), contacts)

        return view
    }
}
