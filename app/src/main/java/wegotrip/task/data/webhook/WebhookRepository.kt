package wegotrip.task.data.webhook

import wegotrip.task.data.model.SaveReviewResponse

interface WebhookRepository {

    suspend fun saveReview(): SaveReviewResponse

}