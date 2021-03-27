package com.cnd.birdapps.ui.view.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.cnd.birdapps.databinding.ActivityUserBinding
import com.cnd.birdapps.ui.viewmodels.UserViewModel
import com.cnd.birdapps.utils.FileUtil
import id.zelory.compressor.Compressor
import id.zelory.compressor.loadBitmap
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException


class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private val viewModel: UserViewModel by viewModels()

    companion object {
        private const val PICK_IMAGE_REQUEST_1 = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testApi.setOnClickListener {
            viewModel.setData("1")
        }

        viewModel.items.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })

        viewModel.failure.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })

        binding.upload.setOnClickListener {
            viewModel.setDataPost()
        }
        binding.fotoKondisiAtap1.setOnClickListener {
            uploadImage(PICK_IMAGE_REQUEST_1)
        }
    }

    private fun uploadImage(requestCode: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_IMAGE_REQUEST_1 -> {
                if (requestCode == PICK_IMAGE_REQUEST_1 && resultCode == RESULT_OK) {
                    if (data == null) {
                        showError("Failed to open picture!")
                        return
                    }
                    try {
                        FileUtil.from(this, data.data)?.also { imageFile ->
                            lifecycleScope.launch {
                                // Default compression
                                val compressedImage = Compressor.compress(
                                    this@UserActivity,
                                    imageFile
                                )
                                val file = Uri.fromFile(compressedImage)
//                                Log.d("gambar",file.toString())

                                uploadImageDua(compressedImage)
                                binding.fotoKondisiAtap1.setImageBitmap(loadBitmap(compressedImage))
                            }
                        }
                    } catch (e: IOException) {
                        showError("Failed to read picture data!")
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private fun uploadImageDua(file: File) {

        val userId: RequestBody = RequestBody.create(MultipartBody.FORM, "2")
        val birdSpecies: RequestBody = RequestBody.create(MultipartBody.FORM, "1")
        val description: RequestBody = RequestBody.create(MultipartBody.FORM, "descriptionString")

        val img: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val image = MultipartBody.Part.createFormData("image", file.name, img)

        viewModel.setArticlePost(userId, birdSpecies, description, image)
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}