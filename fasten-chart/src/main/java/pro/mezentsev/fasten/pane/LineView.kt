package pro.mezentsev.fasten.pane

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import pro.mezentsev.fasten.model.Line

internal class LineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val OUTER_RADIUS = 10f
        private const val INNER_RADIUS = 5f
    }

    private val paint = Paint().apply {
        strokeWidth = 5f
    }

    private val paintInner = Paint().apply {
        strokeWidth = 5f
        color = Color.WHITE
    }

    private var line: Line? = null

    fun setLine(line: Line) {
        this.line = line
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        val line = this.line ?: return
        for ((i, curPoint) in line.points.withIndex()) {
            paint.color = line.color

            if (i > 0) {
                val prevPoint = line.points[i - 1]
                canvas.drawLine(prevPoint.x, prevPoint.y, curPoint.x, curPoint.y, paint)
            }

            canvas.drawCircle(
                curPoint.x,
                curPoint.y,
                OUTER_RADIUS,
                paint
            )

            canvas.drawCircle(
                curPoint.x,
                curPoint.y,
                INNER_RADIUS,
                paintInner
            )
        }
    }
}