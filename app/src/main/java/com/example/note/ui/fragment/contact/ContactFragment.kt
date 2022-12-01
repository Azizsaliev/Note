package com.example.note.ui.fragment.contact
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import com.example.note.base.BaseFragment
import com.example.note.databinding.FragmentContactBinding
import com.example.note.model.ContactModel

class ContactFragment : BaseFragment<FragmentContactBinding>(FragmentContactBinding::inflate),ContactAdapter.ShareContactInterface{
    private lateinit var adapter: ContactAdapter

    override fun setupUi() {
        adapter = ContactAdapter(this)
        binding.recyclerContact.adapter = adapter
        getContact()
    }

    private fun getContact() {
        val list = ArrayList<ContactModel>()

        val contentResolver = requireActivity().contentResolver
        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
            null,null,null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        if (cursor?.count!! > 0) {
            while (cursor.moveToNext())
                if (Integer.parseInt(
                        cursor.getString
                            (cursor.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER).toInt())) > 0 ) {

                          val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)?:0)
                          val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)?:0)

                    val phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?",
                    arrayOf(id),null)
                    if (phoneCursor?.moveToNext()!!){
                        val phoneNumber =
                     phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)?:0)
                     phoneCursor.close()
                        list.add(ContactModel(name , phoneNumber))
                    }
            }
        cursor.close()
        adapter.setList(list)
        }
    }

    override fun share(number: String, shareSwitch: Boolean) {
        if (shareSwitch){
            AlertDialog.Builder(requireContext())
                .setTitle("Перейти на номер $number?")
                .setNegativeButton("Нет",null)
                .setPositiveButton("Да"){ _: DialogInterface, _: Int ->
                    val url = "https://api.whatsapp.com/send?phone=$number"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = (Uri.parse(url))
                    requireActivity().startActivity(intent)
                }.show()
        }else {
            AlertDialog.Builder(requireContext())
                .setTitle("Перейти  на номер $number?")
                .setNegativeButton("нет", null)
                .setPositiveButton("Да") { _: DialogInterface, _: Int ->
                    val intent =
                        Intent(
                            Intent.ACTION_DIAL,
                            Uri.fromParts("tel", number, null)
                        )
                    requireActivity().startActivity(intent)
                }.show()
        }
    }
}
