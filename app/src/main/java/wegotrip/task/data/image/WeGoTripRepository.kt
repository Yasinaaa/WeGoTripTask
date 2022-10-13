package wegotrip.task.data.image

import wegotrip.task.data.model.ImageResponse

interface WeGoTripRepository {

    suspend fun getImage(): ImageResponse

}