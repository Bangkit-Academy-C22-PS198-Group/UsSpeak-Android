package com.example.usspeak.model.page

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usspeak.adapter.HistoryAdapter
import com.example.usspeak.api.TokenPref
import com.example.usspeak.databinding.FragmentHistoryBinding
import com.example.usspeak.model.viewmodel.HistoryViewModel
import com.example.usspeak.response.HistoryResponse

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HistoryViewModel
    private lateinit var tokenPref: TokenPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
        tokenPref = TokenPref((activity?.applicationContext ?: "") as Context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        binding.rvHistory.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        viewModel.getHistory(tokenPref.getToken())
        viewModel.observableHistory.observe(viewLifecycleOwner) {
            showLoading(false)
            if (it != null) {
                setList(it)
            }
        }
        viewModel.observableError.observe(viewLifecycleOwner) { data ->
            showLoading(false)
            Log.e(TAG, data?.message.toString())
            Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setList(data: HistoryResponse) {
        val listHistory = listOf(data)

        val adapter = HistoryAdapter(listHistory, context)
        binding.rvHistory.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "HistoryFragment"
    }
}