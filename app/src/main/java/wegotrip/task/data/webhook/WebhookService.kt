package wegotrip.task.data.webhook

import retrofit2.http.POST
import wegotrip.task.data.model.SaveReviewResponse


interface WebhookService {

    //понимаю, что этот ключ не должен был быть внутри Post, а отправлен через аргументы функции,
    // но у меня не хватило времени доделать
    @POST("c8f2041c-c57e-433f-853f-1ef739702903")
    suspend fun saveReview(): SaveReviewResponse

}