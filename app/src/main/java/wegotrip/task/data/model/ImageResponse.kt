package wegotrip.task.data.model

data class ImageResponse(
    val data: Data
) {
    class Data(
        val images: List<Image>
    ) {
        class Image(
            val id: String,
            val preview: String
        )
    }
}