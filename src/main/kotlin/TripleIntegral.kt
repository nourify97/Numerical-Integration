/**
 * The definition of a triple integral
 */
data class TripleIntegral(
    val lowerX: Double,
    val upperX: Double,
    val lowerY: Double,
    val upperY: Double,
    val lowerZ: Double,
    val upperZ: Double,
    val func: (Double, Double, Double) -> Double
)
