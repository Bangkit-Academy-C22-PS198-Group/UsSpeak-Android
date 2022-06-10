package com.example.usspeak.model.page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.usspeak.api.TokenPref
import com.example.usspeak.databinding.FragmentProfileBinding
import com.example.usspeak.model.viewmodel.UserViewModel


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var tokenPref: TokenPref
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenPref = TokenPref((activity?.applicationContext ?: "") as Context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(false)
        viewModel.getUser(tokenPref.getToken())

        viewModel.observableUser.observe(viewLifecycleOwner) { data ->
            binding.tvName.text = data?.name
            binding.tvEmail.text = data?.email
        }

        viewModel.observableError.observe(viewLifecycleOwner) { err ->
            Toast.makeText(context, err.message, Toast.LENGTH_SHORT).show()
        }

        binding.btnPencil.setOnClickListener {

        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}