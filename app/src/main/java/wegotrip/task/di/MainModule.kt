package wegotrip.task.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit
import wegotrip.task.data.webhook.WeGoTripRepository
import wegotrip.task.domain.WeGoTripRepositoryImpl
import wegotrip.task.data.webhook.WeGoTripService
import javax.inject.Named

@Module
@InstallIn(ActivityRetainedComponent::class)
object MainModule {

    @Provides
    @ActivityRetainedScoped
    fun providePromocodeRepository(
        @Named(REQUEST_WEBHOOK) retrofit: Retrofit
    ): WeGoTripRepository = WeGoTripRepositoryImpl(retrofit.create(WeGoTripService::class.java))

}