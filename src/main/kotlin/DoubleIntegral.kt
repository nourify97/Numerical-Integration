/**
 * The definition of a double integral
 */
data class DoubleIntegral(
    val lowerX: Double,
    val upperX: Double,
    val lowerY: Double,
    val upperY: Double,
    val func: (Double, Double) -> Double
)

