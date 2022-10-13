package wegotrip.task.data.image

import retrofit2.http.GET
import wegotrip.task.data.model.ImageResponse


interface WeGoTripService {

    @GET("products/3728/")
    suspend fun getImage(): ImageResponse

}