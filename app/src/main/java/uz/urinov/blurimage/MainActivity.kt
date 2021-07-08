package uz.urinov.blurimage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import uz.urinov.blurimage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: BlurViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(BlurViewModel::class.java)


        binding.go.setOnClickListener {
            viewModel.applyBlur(5)
        }

        val intent = intent

        val imageUriExtra = intent.getStringExtra(Constants.KEY_IMAGE_URI)

        viewModel.setImageUri(imageUriExtra!!)

        binding.image.load(imageUriExtra)

    }
}