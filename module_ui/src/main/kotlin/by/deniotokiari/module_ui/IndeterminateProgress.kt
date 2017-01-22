package by.deniotokiari.module_ui

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class IndeterminateProgress(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    init {
        val array: TypedArray? = context?.obtainStyledAttributes(attrs, R.styleable.IndeterminateProgress)

        array?.let {
            primaryColor = it.getColor(R.styleable.IndeterminateProgress_primary_color, 0)
            secondaryColor = it.getColor(R.styleable.IndeterminateProgress_secondary_color, 0)

            primaryWidth = it.getDimensionPixelSize(R.styleable.IndeterminateProgress_primary_width, 0)
            secondaryWidth = it.getDimensionPixelSize(R.styleable.IndeterminateProgress_secondary_width, 0)

            speed = it.getInt(R.styleable.IndeterminateProgress_speed, 0)
        }

        array?.recycle()
    }

    private var primaryColor: Int = 0
    private var secondaryColor: Int = 0

    private var primaryWidth: Int = 0
    private var secondaryWidth: Int = 0

    private var speed: Int = 0

    private val primaryPaint: Paint by lazy {
        val paint = Paint()

        paint.color = primaryColor

        paint
    }

    private val secondaryPaint: Paint by lazy {
        val paint = Paint()

        paint.color = secondaryColor

        paint
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawColor(Color.TRANSPARENT)

        canvas?.width?.div(primaryWidth + secondaryWidth)?.let {
            var offset = 0

            0.rangeTo(it).forEach {
                canvas.drawRect(offset.toFloat(), 0F, (offset + primaryWidth).toFloat(), canvas.height.toFloat(), primaryPaint)
                canvas.drawRect(offset.toFloat() + primaryWidth, 0F, (offset + primaryWidth + secondaryWidth).toFloat(), canvas.height.toFloat(), secondaryPaint)

                offset += primaryWidth + secondaryWidth
            }
        }
    }

}