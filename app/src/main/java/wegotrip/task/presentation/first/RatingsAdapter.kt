package wegotrip.task.presentation.first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wegotrip.task.data.model.ReviewModel
import wegotrip.task.databinding.ItemRatingBinding

class RatingsAdapter(
    private val items: List<ReviewModel>
): RecyclerView.Adapter<RatingsAdapter.ItemRowHolder>() {

    private var selectedRatings: HashMap<String, Int> = hashMapOf<String, Int>().apply {
        items.forEach {
            this[it.id] = 0
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemRowHolder(
            ItemRatingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ItemRowHolder, position: Int) {
        holder.bind(items[position]) { id, value ->
            selectedRatings[id] = value
        }
    }

    override fun getItemCount() = items.size

    fun getSelectedRatings() = selectedRatings

    class ItemRowHolder(
        private val binding: ItemRatingBinding,
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(
            review: ReviewModel,
            onItemSelected: (String, Int) -> Unit
        ) = with(binding) {
            textRatingTitle.text = review.title
            ratingBar.onThumbPositionChanged {
                //todo change emoji
                onItemSelected(review.id, it)
            }
        }
    }
}