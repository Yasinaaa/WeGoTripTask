package wegotrip.task.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import wegotrip.task.data.model.ReviewModel
import wegotrip.task.databinding.FragmentFirstBinding

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val ratingsAdapter by lazy {
        RatingsAdapter(
            listOf(
                ReviewModel(
                    id = "key1",
                    title = "Как вам тур в целом?",
                    textField1 = "",
                    textField2 = ""
                ),
                ReviewModel(
                    id = "key1",
                    title = "Понравился гид?",
                    textField1 = "",
                    textField2 = ""
                ),
                ReviewModel(
                    id = "key1",
                    title = "Как вам подача информации?",
                    textField1 = "",
                    textField2 = ""
                ),
                ReviewModel(
                    id = "key1",
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
        with(binding.recyclerViewRatings) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ratingsAdapter
        }
    }

}