package com.example.usspeak.model.page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.usspeak.R
import com.example.usspeak.api.TokenPref
import com.example.usspeak.databinding.FragmentChangeProfileBinding
import com.example.usspeak.model.viewmodel.UserViewModel

class ChangeProfileFragment : Fragment() {
    private var _binding: FragmentChangeProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private lateinit var tokenPref: TokenPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenPref = TokenPref((activity?.applicationContext ?: "") as Context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.edtEmail.isEnabled = false

        binding.edtEmail.hint = EMAIL
        binding.edtName.hint = NAME

        binding.btnSubmit.setOnClickListener {
            val name = binding.edtName.text.toString().trim()
            viewModel.renameUser(name, tokenPref.getToken())
            viewModel.observableUser.observe(viewLifecycleOwner) {
                Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.fragment_profile)
            }

            viewModel.observableError.observe(viewLifecycleOwner) {
                Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        var EMAIL = "email"
        var NAME = "name"
    }
}