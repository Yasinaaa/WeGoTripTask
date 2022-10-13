package wegotrip.task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import wegotrip.task.data.webhook.WebhookRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WebhookRepository
): ViewModel() {

    fun onSaveReview() = viewModelScope.launch {
        try {
            repository.saveReview()
        } catch (e: Exception) {
        }
    }
}