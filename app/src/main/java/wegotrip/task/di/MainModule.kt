package wegotrip.task.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit
import wegotrip.task.data.image.WeGoTripRepository
import wegotrip.task.data.image.WeGoTripService
import wegotrip.task.domain.WeGoTripRepositoryImpl
import wegotrip.task.data.webhook.WebhookRepository
import wegotrip.task.data.webhook.WebhookService
import wegotrip.task.domain.WebhookRepositoryImpl
import javax.inject.Named

@Module
@InstallIn(ActivityRetainedComponent::class)
object MainModule {

    @Provides
    @ActivityRetainedScoped
    fun provideWebhookRepository(
        @Named(REQUEST_WEBHOOK) retrofit: Retrofit
    ): WebhookRepository = WebhookRepositoryImpl(retrofit.create(WebhookService::class.java))

    @Provides
    @ActivityRetainedScoped
    fun provideWeGoTripRepository(
        @Named(IMAGES_REQUEST) retrofit: Retrofit
    ): WeGoTripRepository = WeGoTripRepositoryImpl(retrofit.create(WeGoTripService::class.java))

}