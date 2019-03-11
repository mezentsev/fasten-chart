package pro.mezentsev.fasten.sample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.widget.Button
import pro.mezentsev.fasten.ChartView
import pro.mezentsev.fasten.model.Line
import pro.mezentsev.fasten.model.Point
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class ChartActivity : AppCompatActivity() {
    private var executorService = Executors.newSingleThreadScheduledExecutor()
    private var handler = Handler()

    private lateinit var runnable: Runnable
    private lateinit var future: ScheduledFuture<*>
    private lateinit var chartView1: ChartView
    private lateinit var chartView2: ChartView
    private lateinit var chartView3: ChartView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chartView1 = findViewById(R.id.chart_view1)
        chartView2 = findViewById(R.id.chart_view2)
        chartView3 = findViewById(R.id.chart_view3)

        runnable = Runnable {
            Thread.sleep(600)

            val lines = mutableListOf<Line>()
            for (l in 0..3) {
                val points = mutableListOf<Point>()
                for (p in 0..7) {
                    points.add(Point(p * 10f, (Random.nextFloat() * 100f)))
                }
                lines.add(Line(points, Color.rgb(l * 30, l * 60, l * 90)))
            }

            handler.post {
                chartView1.setData(lines)
                chartView2.setData(lines)
                chartView3.setData(lines)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        future = executorService.scheduleAtFixedRate(runnable, 0, 700, TimeUnit.MILLISECONDS)
    }

    override fun onPause() {
        super.onPause()
        future.cancel(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        executorService.shutdownNow()
    }
}
