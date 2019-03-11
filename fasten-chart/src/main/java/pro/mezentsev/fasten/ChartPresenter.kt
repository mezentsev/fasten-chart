package pro.mezentsev.fasten

import android.graphics.Color
import pro.mezentsev.fasten.model.Pane
import pro.mezentsev.fasten.model.Line
import pro.mezentsev.fasten.model.Point
import pro.mezentsev.fasten.pane.LineView

internal class ChartPresenter(private val chartView: ChartMvpView) {

    private var lines: List<Line>? = null
    private var width: Float = 0f
    private var height: Float = 0f

    fun setData(lines: List<Line>, width: Float, height: Float) {
        this.lines = lines
        this.width = width
        this.height = height

        update()
    }

    private fun update() {
        val l = this.lines ?: return

        var fromX = Float.POSITIVE_INFINITY
        var toX = Float.NEGATIVE_INFINITY
        var fromY = Float.POSITIVE_INFINITY
        var toY = Float.NEGATIVE_INFINITY

        for (line in l) {
            for (point in line.points) {
                if (point.x > toX) {
                    toX = point.x
                }

                if (point.x < fromX) {
                    fromX = point.x
                }

                if (point.y > toY) {
                    toY = point.y
                }

                if (point.y < fromY) {
                    fromY = point.y
                }
            }
        }

        val paneHeight = toY - fromY
        val paneWidth = toX - fromX

        val translateY = fromY
        val translateX = fromX

        val mulSizeY = height / paneHeight
        val mulSizeX = width / paneWidth

        val shownLines = ArrayList<Line>(l.size)
        for (line in l) {
            val shownPoints = ArrayList<Point>(line.points.size)
            line.points.forEach {
                shownPoints.add(
                    it.copy(x = (it.x - translateX) * mulSizeX, y = (it.y - translateY) * mulSizeY)
                )
            }

            shownLines.add(line.copy(points = shownPoints))
        }

        val pane = Pane(
            fromX - translateX, fromY - translateY,
            (toX - translateX) * mulSizeX, (toY - translateY) * mulSizeY
        )

        val selectedLine = Line(listOf(Point(x = 150f, y = 0f), Point(150f, height)), Color.RED)
        shownLines.add(selectedLine)
        chartView.showCharts(pane, shownLines)
    }
}