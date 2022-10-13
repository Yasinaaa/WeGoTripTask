package wegotrip.task.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import wegotrip.task.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOpenBottomSheet.setOnClickListener {
            BottomSheetFragment().show(supportFragmentManager, BottomSheetFragment.TAG)
        }
    }
}