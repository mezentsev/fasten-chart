package pro.mezentsev.fasten

import pro.mezentsev.fasten.model.Line
import pro.mezentsev.fasten.model.Pane

internal interface ChartMvpView {

    fun showCharts(pane: Pane,
                   lines: Collection<Line>)
}