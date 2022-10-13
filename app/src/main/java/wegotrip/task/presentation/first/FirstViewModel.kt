package wegotrip.task.presentation.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import wegotrip.task.data.image.WeGoTripRepository
import wegotrip.task.data.model.ImageResponse
import wegotrip.task.data.webhook.WebhookRepository
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val webhookRepository: WebhookRepository,
    private val weGoTripRepository: WeGoTripRepository
): ViewModel() {

    val imageFlow = MutableStateFlow<ImageResponse.Data.Image?>(null)

    fun onSaveReview() = viewModelScope.launch {
        try {
            webhookRepository.saveReview()
        } catch (e: Exception) {
        }
    }

    fun getImage() = viewModelScope.launch {
        try {
            imageFlow.value = weGoTripRepository.getImage().data.images[0]
        } catch (e: Exception) {
            //todo errorFlow
        }
    }
}