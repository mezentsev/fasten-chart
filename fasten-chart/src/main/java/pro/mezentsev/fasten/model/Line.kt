package pro.mezentsev.fasten.model

import androidx.annotation.ColorInt

data class Line @JvmOverloads constructor(val points: List<Point>,
                                          @ColorInt val color: Int,
                                          val description: String? = null)