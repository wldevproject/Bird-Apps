package com.cnd.birdapps.ui.view.article

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.cnd.birdapps.R
import com.cnd.birdapps.databinding.ActivityInputArticleBinding
import com.cnd.birdapps.ui.viewmodels.InputArticleViewModel
import com.cnd.birdapps.utils.ConsData.userID
import com.cnd.birdapps.utils.FileUtil
import id.zelory.compressor.Compressor
import id.zelory.compressor.loadBitmap
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException

@Suppress("DEPRECATION")
class InputArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputArticleBinding
    private val viewModel: InputArticleViewModel by viewModels()

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            uploadImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, PICK_IMAGE_REQUEST)
            }
            nonActiveBtn()
            progressBar.visibility = View.GONE
            viewPrograss.visibility = View.GONE
        }

        viewModel.items.observe(this, Observer {
            nonActiveBtn()
            binding.progressBar.visibility = View.GONE
            binding.viewPrograss.visibility = View.GONE
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })

        viewModel.failure.observe(this, Observer {
            nonActiveBtn()
            binding.progressBar.visibility = View.GONE
            binding.viewPrograss.visibility = View.GONE
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data == null) {
                showError("Failed to open picture!")
                return
            }
            try {
                FileUtil.from(this, data.data)?.also { imageFile ->
                    lifecycleScope.launch {
                        // Default compression
                        val compressedImage = Compressor.compress(
                            this@InputArticleActivity,
                            imageFile
                        )
//                        if you need uri file, you can use this, file convert to uri
//                        val file = Uri.fromFile(compressedImage)

                        uploadImage(compressedImage)
                        binding.uploadImage.setImageBitmap(loadBitmap(compressedImage))
                    }
                }
            } catch (e: IOException) {
                showError("Failed to read picture data!")
                e.printStackTrace()
            }
        }
    }

    private fun uploadImage(file: File) {
        binding.btnUpload.isClickable = true
        binding.btnUpload.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))

        val userId: RequestBody = RequestBody.create(MultipartBody.FORM, userID.toString())
        val birdSpecies: RequestBody = RequestBody.create(MultipartBody.FORM, "2")
        val description: RequestBody = RequestBody.create(MultipartBody.FORM, "descriptionString")

        val img: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val image = MultipartBody.Part.createFormData("image", file.name, img)

        binding.btnUpload.setOnClickListener {
            nonActiveBtn()
            binding.progressBar.visibility = View.VISIBLE
            binding.viewPrograss.visibility = View.VISIBLE
            viewModel.setArticlePost(userId, birdSpecies, description, image)
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun nonActiveBtn() {
        binding.btnUpload.isClickable = false
        binding.btnUpload.setBackgroundColor(ContextCompat.getColor(this, R.color.grey))
    }
}