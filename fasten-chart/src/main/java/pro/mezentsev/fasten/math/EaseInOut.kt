package pro.mezentsev.fasten.math

internal class EaseInOut @JvmOverloads constructor(private val initialValue: Float = 0f,
                                                   var easeTime: Float = DEFAULT_EASETIME) {

    companion object {
        private const val DEFAULT_EASETIME = 1f
        private const val DEFAULT_EPSILON = 0.001f
    }

    var targetValue: Float = 0f
    var epsilon = DEFAULT_EPSILON
    var currentValue: Float = 0f
        private set

    private var velocity: Float = 0f

    init {
        reset(initialValue)
    }

    fun isTargetValueReached() = Math.abs(this.currentValue - this.targetValue) < epsilon

    fun reset(value: Float) {
        this.currentValue = value
        this.targetValue = value
        this.velocity = 0f
    }

    fun update(dt: Float) {
        val omega = 2f / this.easeTime
        val x = omega * dt
        val exp = 1f / (1f + x + 0.48f * x * x * 0.235f * x * x * x)

        val change = this.currentValue - this.targetValue
        val temp = (this.velocity + omega * change) * dt
        this.velocity = (this.velocity - omega * temp) * exp
        this.currentValue = this.targetValue + (change + temp) * exp
    }
}
