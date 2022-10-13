package wegotrip.task.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import wegotrip.task.R

class ReviewRatingBar(
    context: Context,
    attributeSet: AttributeSet
): View(context, attributeSet) {

    private var thumbX: Float = 0f
    private val linePaint = Paint()
    private val circlePaint = Paint()
    private val thumbPaint = Paint()
    private val canvasHeight by lazy {
        context.resources.getDimension(
            R.dimen.colorful_seekbar_canvas_height
        ).toInt()
    }
    private val paddingHorizontal by lazy {
        context.resources.getDimension(R.dimen.rating_bar_horizontal_padding)
    }
    private val smallUnselectedCircleRadius by lazy {
        context.resources.getDimension(R.dimen.small_unselected_circle_radius)
    }
    private val ratingBarThumbRadius by lazy {
        context.resources.getDimension(R.dimen.rating_bar_thumb_radius)
    }
    private val fiveCirclesPositions by lazy {
        listOf(
            paddingHorizontal,
            (width.toFloat() + paddingHorizontal) / 4,
            (width.toFloat() + paddingHorizontal) / 4,
            (width.toFloat() * 2)/ 4,
            (width.toFloat() * 3 - paddingHorizontal)/ 4,
            width.toFloat() - paddingHorizontal
        )
    }
    private var onChangeThumbPosition: ((Int) -> Unit)? = null

    init {
        thumbX = paddingHorizontal
        circlePaint.color = Color.GRAY
        circlePaint.isAntiAlias = true
        thumbPaint.color = Color.WHITE
        thumbPaint.setShadowLayer(
            5f, 0f, 0f, ContextCompat.getColor(context, R.color.purple_200)
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(
            paddingHorizontal, paddingHorizontal,
            width.toFloat() - paddingHorizontal, paddingHorizontal,
            linePaint
        )

        fiveCirclesPositions.forEach { position ->
            canvas?.drawCircle(
                position,
                paddingHorizontal, smallUnselectedCircleRadius, circlePaint
            )
        }

//        canvas?.drawCircle(
//            paddingHorizontal,
//            paddingHorizontal, smallUnselectedCircleRadius, circlePaint)
//        canvas?.drawCircle(
//            (width.toFloat() + paddingHorizontal) / 4,
//            paddingHorizontal, smallUnselectedCircleRadius, circlePaint)
//        canvas?.drawCircle(
//            (width.toFloat() * 2)/ 4,
//            paddingHorizontal, smallUnselectedCircleRadius, circlePaint)
//        canvas?.drawCircle(
//            (width.toFloat() * 3 - paddingHorizontal)/ 4,
//            paddingHorizontal, smallUnselectedCircleRadius, circlePaint)
//        canvas?.drawCircle(
//            width.toFloat() - paddingHorizontal,
//            paddingHorizontal, smallUnselectedCircleRadius, circlePaint)

        canvas?.drawCircle(thumbX, paddingHorizontal, ratingBarThumbRadius, thumbPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, canvasHeight)
    }

    private fun List<Float>.closestValue(value: Float) = minByOrNull {
        kotlin.math.abs(value - it)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(
        event: MotionEvent?
    ): Boolean {
        when(event?.action){
            MotionEvent.ACTION_UP -> {
                parent.requestDisallowInterceptTouchEvent(true)
                //val old = thumbX
                thumbX = fiveCirclesPositions.closestValue(event.x) ?: 0f
                onChangeThumbPosition?.invoke(getCurrentThumbPosition())
                //if (old != thumbX) {
                    invalidate()
                //}
            }
        }
        return true
    }

    fun onThumbPositionChanged(
        onChange: (Int) -> Unit
    ) {
       onChangeThumbPosition = onChange
    }

    fun getCurrentThumbPosition() = fiveCirclesPositions.indexOf(thumbX)
}