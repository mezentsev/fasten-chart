package pro.mezentsev.fasten.pane

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import pro.mezentsev.fasten.model.Line
import pro.mezentsev.fasten.model.Pane
import pro.mezentsev.fasten.model.Point

class PaneView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    private var pane: Pane? = null
    private var lines: Collection<Line>? = null

    fun setPane(pane: Pane, lines: Collection<Line>) {
        this.pane = pane
        this.lines = lines

        lines.forEach {
            val lineView = LineView(context).apply {
                this.setLine(it)
            }
            addViewInLayout(lineView, childCount, generateDefaultLayoutParams())
        }

        addViewInLayout(GridView(context), childCount, generateDefaultLayoutParams())
    }
}