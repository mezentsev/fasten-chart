package pro.mezentsev.fasten

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import pro.mezentsev.fasten.model.Line
import pro.mezentsev.fasten.model.Pane
import pro.mezentsev.fasten.pane.PaneView

class ChartView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    var isDebug: Boolean = false
        set(value) {
            field = value
            debugAnimator.cancel()

            if (value) {
                debugAnimator.start()
            } else {
                debugAnimator.reverse()
            }
        }

    private val presenter = ChartPresenter(MvpView())
    private val debugAnimator = ObjectAnimator.ofInt(0, 50).apply {
        addUpdateListener {
            debugPaint.color = Color.argb(it.animatedValue as Int, 0, 50, 0)
            invalidate()
        }
    }
    private val debugPaint: Paint by lazy {
        Paint().apply { color = Color.WHITE }
    }

    fun setData(lines: List<Line>) {
        presenter.setData(lines, width.toFloat(), height.toFloat())
    }

    internal inner class MvpView : ChartMvpView {
        override fun showCharts(pane: Pane,
                                lines: Collection<Line>) {
            removeAllViewsInLayout()

            val paneView = PaneView(context).apply {
                setPane(pane, lines)
            }

            addViewInLayout(paneView, childCount, generateDefaultLayoutParams())

            requestLayout()
        }
    }
}