package wegotrip.task.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import wegotrip.task.data.image.WeGoTripRepository
import wegotrip.task.data.image.WeGoTripService
import javax.inject.Inject

class WeGoTripRepositoryImpl @Inject constructor(
    private val service: WeGoTripService
) : WeGoTripRepository {

    override suspend fun getImage() = withContext(Dispatchers.IO) {
        service.getImage()
    }
}