package dev.androidbroadcast.contactapp

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract

data class Contact(
    val name: String,
    val phoneNumber: String,
    val photoUri: String?
)

object ContactHelper {

    fun getContacts(context: Context): List<Contact> {
        val contacts = mutableListOf<Contact>()

        val cursor: Cursor? = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI
            ),
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )

        cursor?.use {
            val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val photoIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)

            while (it.moveToNext()) {
                val name = it.getString(nameIndex)
                val phoneNumber = it.getString(numberIndex)
                val photoUri = it.getString(photoIndex)

                contacts.add(Contact(name, phoneNumber, photoUri))
            }
        }

        return contacts
    }
}

