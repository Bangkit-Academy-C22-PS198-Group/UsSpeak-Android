package com.example.usspeak.model.page

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.usspeak.api.TokenPref
import com.example.usspeak.databinding.FragmentInputBinding
import com.example.usspeak.model.viewmodel.HistoryViewModel
import com.example.usspeak.uriToFile
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class InputFragment : Fragment() {
    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!
    private lateinit var tokenPref: TokenPref
    private var getFile: File? = null
    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
        tokenPref = TokenPref((activity?.applicationContext ?: "") as Context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(false)

        binding.btnBrowse.setOnClickListener {
            startGallery()
            if (getFile != null) {
                binding.tvFileName.text = getFile?.name.toString()
                Log.e(TAG, getFile?.name.toString())
            }
        }

        binding.btnSubmit.setOnClickListener {
            showLoading(true)
            if (getFile != null) {
                val requestAudioFile = getFile!!.asRequestBody("audio/mp3".toMediaTypeOrNull())
                val audioMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "file",
                    getFile!!.name,
                    requestAudioFile
                )
                viewModel.uploadAudio(tokenPref.getToken(), audioMultipart)
                viewModel.observableAudio.observe(viewLifecycleOwner) { data ->
                    showLoading(false)
                    Toast.makeText(context, data?.message, Toast.LENGTH_SHORT).show()
                }

                viewModel.observableError.observe(viewLifecycleOwner) { data ->
                    Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
                }
            } else {
                showLoading(false)
                Toast.makeText(context, "No audio is selected.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "audio/*"
        val chooser = Intent.createChooser(intent, "Choose an audio file")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedAudio: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedAudio, (activity?.applicationContext ?: "") as Context)
            getFile = myFile
            Log.e(TAG, getFile!!.absolutePath)
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

    companion object {
        const val TAG = "InputFragment"
    }

}