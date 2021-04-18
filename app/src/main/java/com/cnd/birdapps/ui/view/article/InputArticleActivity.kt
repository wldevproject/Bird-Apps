package com.cnd.birdapps.ui.view.article

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.cnd.birdapps.R
import com.cnd.birdapps.databinding.ActivityInputArticleBinding
import com.cnd.birdapps.ui.viewmodels.InputArticleViewModel
import com.cnd.birdapps.ui.viewmodels.KategoryViewModel
import com.cnd.birdapps.utils.ConsData.stateKategory
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
    private val listViewModel: KategoryViewModel by viewModels()
    private val viewModel: InputArticleViewModel by viewModels()
    private var idBird = 0

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        spinnerData()

        binding.apply {
            uploadImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, PICK_IMAGE_REQUEST)
            }
            nonActiveBtn()
            loadGone()
        }

        viewModel.status.observe(this, Observer {
            nonActiveBtn()
            loadGone()
            toastText(it)
            finish()
        })
    }

    private fun spinnerData() {
        listViewModel.getData(stateKategory)
        listViewModel.items.observe(this, Observer {
            val birdSpecies: ArrayList<String> = arrayListOf()
            for (data in it) {
                birdSpecies.add("${data.id}. ${data.name}")
            }
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                this,
                R.layout.item_spinner, birdSpecies
            )
            adapter.setDropDownViewResource(R.layout.item_spinner)
            binding.listJenisBurung.adapter = adapter
        })


        binding.listJenisBurung.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                    idBird = position + 1
//                    val selectedName = parent.getItemAtPosition(position).toString()
//                    chooseBird("${position + 1}")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data == null) {
                toastText("Failed to open picture!")
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
                toastText("Failed to read picture data!")
                e.printStackTrace()
            }
        }
    }

    private fun uploadImage(file: File) {
        activeBtn()

        binding.btnUpload.setOnClickListener {
            val txtDescription = binding.description.text.toString().trim { it <= ' ' }
            val userId: RequestBody = RequestBody.create(MultipartBody.FORM, userID.toString())
            val birdSpecies: RequestBody = RequestBody.create(MultipartBody.FORM, idBird.toString())
            val description: RequestBody = RequestBody.create(MultipartBody.FORM, txtDescription)

            val img: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val image = MultipartBody.Part.createFormData("image", file.name, img)
//            toastText("$userID=$userId\n$idBird=$birdSpecies\n$txtDescription")
            viewModel.setArticlePost(userId, birdSpecies, description, image)

            loadVisible()
            nonActiveBtn()
        }
    }

    private fun toastText(text: String): String {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        return text
    }

    private fun nonActiveBtn() {
        binding.btnUpload.isClickable = false
        binding.btnUpload.setBackgroundColor(ContextCompat.getColor(this, R.color.grey))
    }

    private fun activeBtn() {
        binding.btnUpload.isClickable = true
        binding.btnUpload.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
    }

    private fun loadVisible() {
        binding.progressBar.visibility = View.VISIBLE
        binding.viewPrograss.visibility = View.VISIBLE
    }

    private fun loadGone() {
        binding.progressBar.visibility = View.GONE
        binding.viewPrograss.visibility = View.GONE
    }
}