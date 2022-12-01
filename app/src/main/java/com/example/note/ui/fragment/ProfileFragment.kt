package com.example.note.ui.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.note.R
import com.example.note.base.BaseFragment
import com.example.note.databinding.ActivityMainBinding.inflate
import com.example.note.databinding.FragmentProfileBinding


const val IMAGE_REQUEST_CODE = 100

@Suppress("DEPRECATION")
class PofileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun setupUi() {
        binding.imgProfile.setOnClickListener {
            imageGallery()
        }
    }

    private fun imageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.imgProfile.setImageURI(data?.data)
        }
    }
}
