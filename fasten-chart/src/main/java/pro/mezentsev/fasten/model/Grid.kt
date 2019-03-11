package pro.mezentsev.fasten.model

data class Grid @JvmOverloads constructor(val horizontalOffset: Float = DEFAULT_OFFSET,
                                          val verticalOffset: Float = DEFAULT_OFFSET,
                                          val horizontalDescription: String? = null,
                                          val verticalDescription: String? = null) {
    companion object {
        private const val DEFAULT_OFFSET = 50f
    }
}