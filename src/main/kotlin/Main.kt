import DataSource.doubleIntegrals
import kotlin.math.abs


fun main() {
    val precision = 100
    val doubleIntegral = doubleIntegrals[1]

    val doubleIntegralMidpointEstimation = midpointIntegration(doubleIntegral, precision)
    val doubleIntegralTrapezoidalEstimation = trapezoidalIntegration(doubleIntegral, precision)

    /** Results of triple integral numerical estimation **/
//    val tripleIntegralMidpointEstimation = midpointIntegration(DataSource.tripleIntegral, precision)
//    val tripleIntegralTrapezoidalEstimation = trapezoidalIntegration(DataSource.tripleIntegral, precision)

    val errorEstimations = integrationsErrorEstimation(doubleIntegral, doubleIntegralMidpointEstimation, doubleIntegralTrapezoidalEstimation, precision)

    println("Trapezoidal Rule Integration on ${precision * precision} grids: $doubleIntegralTrapezoidalEstimation Error: ${errorEstimations.second}")
    println("Midpoint Rule Integration on ${precision * precision} grids: $doubleIntegralMidpointEstimation Error: ${errorEstimations.first}")
}

/**
 * This function uses the midpoint rule to approximate the double integral
 * of a function over a rectangular region.
 *
 * @param doubleIntegral object, which holds the function to integrate, the lower and upper bounds of the x and y variables.
 * @param precision integer, number of grids to use in the approximation.
 * @return the approximate value of the double integral.
 */
fun midpointIntegration(doubleIntegral: DoubleIntegral, precision: Int): Double {
    require(precision > 0) { "Precision must be a positive integer" }

    // defining the step size in x and y directions using the precision
    val dx = (doubleIntegral.upperX - doubleIntegral.lowerX) / precision
    val dy = (doubleIntegral.upperY - doubleIntegral.lowerY) / precision

    // initializing the estimation
    var integrationEstimate = 0.0

    // iterating over x and y using the precision as range
    for (i in 0 until  precision) {
        for (j in 0 until  precision) {

            // defining the x and y of the current rectangle center
            val x = doubleIntegral.lowerX + (i + 0.5) * dx
            val y = doubleIntegral.lowerY + (j + 0.5) * dy

            // computing the height using f(x, y),
            // adding the sub volume to the estimation
            integrationEstimate += doubleIntegral.func(x, y) * dx * dy
        }
    }
    return integrationEstimate
}

/**
 * This function uses the midpoint rule to approximate the triple integral
 * of a function over a rectangular region.
 *
 * @param tripleIntegral object, which holds the function to integrate, the lower and upper bounds of the x and y variables.
 * @param precision integer, number of grids to use in the approximation.
 * @return the approximate value of the triple integral.
 */
private fun midpointIntegration(tripleIntegral: TripleIntegral, precision: Int): Double {
    require(precision > 0) { "Precision must be a positive integer" }

    // Defining the steps size in x, y and z directions.
    val dx = (tripleIntegral.upperX - tripleIntegral.lowerX) / precision
    val dy = (tripleIntegral.upperY - tripleIntegral.lowerY) / precision
    val dz = (tripleIntegral.upperZ - tripleIntegral.lowerZ) / precision

    // Initializing the sum.
    var tripleIntegralEstimation = 0.0

    // Iterating over x, y, and z directions.
    for (i in 0 until precision) {
        for (j in 0 until precision) {
            for (l in 0 until precision) {

                // Defining the x, y and z of the midpoint.
                val x = tripleIntegral.lowerX + (i + 0.5) * dx
                val y = tripleIntegral.lowerY + (j + 0.5) * dy
                val z = tripleIntegral.lowerZ + (l + 0.5) * dz

                val result = tripleIntegral.func(x, y, z)

                tripleIntegralEstimation += dx * dy * dz * result
            }
        }
    }

    return tripleIntegralEstimation
}

/**
 * This function uses the trapezoidal rule to approximate the double integral
 * of a function over a rectangular region.
 *
 * @param doubleIntegral object, which holds the function to integrate, the lower and upper bounds of the x and y variables.
 * @param precision integer, number of grids to use in the approximation.
 * @return the approximate value of the double integral.
 */
fun trapezoidalIntegration(doubleIntegral: DoubleIntegral, precision: Int): Double {
    require(precision > 0) { "Precision must be a positive integer" }

    // defining the step size in x and y directions using the precision
    val dx = (doubleIntegral.upperX - doubleIntegral.lowerX) / precision
    val dy = (doubleIntegral.upperY - doubleIntegral.lowerY) / precision

    // initializing the estimation
    var integrationEstimate = 0.0

    // iterating over x and y using the precision as range
    for (i in 0 until precision) {
        for (j in 0 until precision) {

            // defining x and y values of the current grid
            val x1 = doubleIntegral.lowerX + i * dx
            val x2 = doubleIntegral.lowerX + (i + 1) * dx
            val y1 = doubleIntegral.lowerY + j * dy
            val y2 = doubleIntegral.lowerY + (j + 1) * dy

            // Computing the average height
            var avHeight = doubleIntegral.func(x1, y1)
            avHeight += doubleIntegral.func(x1, y2)
            avHeight += doubleIntegral.func(x2, y2)
            avHeight += doubleIntegral.func(x2, y1)
            avHeight /= 4

            // adding the sub volume to the estimation
            integrationEstimate += avHeight * dx * dy
        }
    }

    return integrationEstimate
}

/**
 * This function uses the trapezoidal rule to approximate the triple integral.
 *
 * @param tripleIntegral object, which holds the function to integrate, the lower and upper bounds of the x and y variables.
 * @param precision integer, number of grids to use in the approximation.
 * @return the approximate value of the triple integral.
 */
private fun trapezoidalIntegration(tripleIntegral: TripleIntegral, precision: Int): Double {
    require(precision > 0) { println("Precision must be a positive integer") }

    // Defining the step size in x, y and z directions
    val dx = (tripleIntegral.upperX - tripleIntegral.lowerX) / precision
    val dy = (tripleIntegral.upperY - tripleIntegral.lowerY) / precision
    val dz = (tripleIntegral.upperZ - tripleIntegral.lowerZ) / precision

    // Initializing the sum
    var tripleIntegralEstimation = 0.0

    // Iterating over x, y and z directions
    for (i in 0 until precision) {
        for (j in 0 until precision) {
            for (l in 0 until precision) {

                // Defining all the x, y and z values of this sub-region
                val x0 = tripleIntegral.lowerX + i * dx
                val x1 = tripleIntegral.lowerX + (i+1) * dx
                val y0 = tripleIntegral.lowerY + j * dy
                val y1 = tripleIntegral.lowerY + (j+1) * dy
                val z0 = tripleIntegral.lowerZ + l * dz
                val z1 = tripleIntegral.lowerZ + (l+1) * dz

                var av = tripleIntegral.func(x0, y0, z0)
                av += tripleIntegral.func(x0, y1, z0)
                av += tripleIntegral.func(x0, y1, z1)
                av += tripleIntegral.func(x1, y0, z0)
                av += tripleIntegral.func(x1, y1, z0)
                av += tripleIntegral.func(x1, y1, z1)
                av /= 6

                tripleIntegralEstimation += dx * dy * dz * av
            }
        }
    }

    return tripleIntegralEstimation
}

/**
 * This function calculates the error estimate for both midpoint and trapezoidal integration by
 * comparing their results increasing the precision assuming the finer
 * grid will give us more accurate result.
 *
 * @param doubleIntegral doubleIntegral object, which holds the function to integrate, the lower and upper bounds of the x and y variables.
 * @param midpointEstimation the result of the midpoint integration with the default precision.
 * @param trapezoidalEstimation the result of the trapezoidal integration with the default precision.
 * @param precision the default precision from witch we estimate the error.
 * @return the error estimation for the midpoint and the trapezoidal integration.
 */
fun integrationsErrorEstimation(
    doubleIntegral: DoubleIntegral,
    midpointEstimation: Double,
    trapezoidalEstimation: Double,
    precision: Int
): Pair<Double, Double> {

    // performing integration with precision multiplied by 100
    val finerMidpointEstimation = midpointIntegration(doubleIntegral, precision * 100)
    val finerTrapezoidalEstimation = trapezoidalIntegration(doubleIntegral, precision * 100)

    // finding an estimation of the error by comparing the default precision with a higher precision
    val midpIntegrationError = abs(finerMidpointEstimation - midpointEstimation)
    val trapIntegrationError = abs(finerTrapezoidalEstimation - trapezoidalEstimation)

    return Pair(midpIntegrationError, trapIntegrationError)
}
