package pro.mezentsev.fasten.pane

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.View
import pro.mezentsev.fasten.model.Grid
import pro.mezentsev.fasten.model.Line

internal class GridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        strokeWidth = 5f
        color = Color.argb(30, 0, 0, 0)
    }

    private val paintMiddle = Paint().apply {
        strokeWidth = 5f
        color = Color.RED
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val gridHeight = height.toFloat()
        val gridWidth = width.toFloat()

        // vertical axis
        canvas?.drawLine(0f, 0f, 0f, gridHeight, paint)
        // horizontal axis
        canvas?.drawLine(0f, gridHeight, gridWidth, gridHeight, paint)
        // middle line
        val middleHeight = gridHeight / 2
        canvas?.drawLine(0f, middleHeight, gridWidth, middleHeight, paintMiddle)

        //canvas?.drawRect(0f, 0f, gridWidth, gridHeight, paint)

        // pane of view
        //canvas?.drawRect(pane.fromX, pane.fromY, pane.toX, pane.toY, paint)
    }
}