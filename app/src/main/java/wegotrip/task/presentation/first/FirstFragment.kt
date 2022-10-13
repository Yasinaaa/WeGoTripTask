package wegotrip.task.presentation.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import wegotrip.task.R
import wegotrip.task.data.model.ReviewModel
import wegotrip.task.databinding.FragmentFirstBinding

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FirstViewModel>()
    private val ratingsAdapter by lazy {
        RatingsAdapter(
            //значения должны были браться с репозитория, но так как их нет пока будет так
            listOf(
                ReviewModel(
                    id = "key1",
                    title = "Как вам тур в целом?",
                    textField1 = "",
                    textField2 = ""
                ),
                ReviewModel(
                    id = "key2",
                    title = "Понравился гид?",
                    textField1 = "",
                    textField2 = ""
                ),
                ReviewModel(
                    id = "key3",
                    title = "Как вам подача информации?",
                    textField1 = "",
                    textField2 = ""
                ),
                ReviewModel(
                    id = "key4",
                    title = "Удобная навигация между шагами?",
                    textField1 = "",
                    textField2 = ""
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initViewModel()
    }

    private fun initUi() = with(binding.recyclerViewRatings) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = ratingsAdapter
    }

    private fun initViewModel() {
        //честно говоря, я бы лучше скачала изображение в родительском bottom sheet,
        // потом сюда и 2ой фрагмент отправила в аргументах
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.imageFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
                    it?.let {
                        Glide.with(requireContext())
                            .load(it.preview)
                            .centerCrop()
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(binding.imageAvatar)
                    }
                }
        }
        viewModel.getImage()
    }

}