package pro.mezentsev.fasten.model

data class Point @JvmOverloads constructor(val x: Float,
                                           val y: Float,
                                           val description: String? = null)