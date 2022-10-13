package wegotrip.task.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import wegotrip.task.data.webhook.WebhookRepository
import wegotrip.task.data.webhook.WebhookService
import javax.inject.Inject

class WebhookRepositoryImpl @Inject constructor(
    private val service: WebhookService
) : WebhookRepository {

    override suspend fun saveReview() = withContext(Dispatchers.IO) {
        service.saveReview()
    }

}