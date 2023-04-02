import kotlin.math.*

/**
 * A list of double integrals over a rectangular region.
 */
object DataSource {
    val doubleIntegrals = listOf (
        // symbolic integration = 0.35164991794
        DoubleIntegral(
            lowerX = 0.0, upperX = 1.0,
            lowerY = 0.0, upperY = 1.0,
            func =  { x, y -> ( x*y * sqrt(1 + x.pow(2) + y.pow(2)) ) }
        ),
        // symbolic integration = 5\24
        DoubleIntegral(
            lowerX = 0.0, upperX = 2.0,
            lowerY = 0.0, upperY = 1.0,
            func =  { x, y -> ( 1 / (x + y + 1).pow(3) ) }
        ),
        // symbolic integration = 0
        DoubleIntegral(
            lowerX = 0.0, upperX = 1.0,
            lowerY = PI, upperY = 2* PI,
            func =  { x, y -> ( x * sin(x*y) ) }
        ),
        // symbolic integration = -16
        DoubleIntegral(
            lowerX = -1.0, upperX = 1.0,
            lowerY = 0.0, upperY = 2.0,
            func =  { x, y -> ( 2*x - 3*y.pow(2) ) }
        ),
        // symbolic integration = 2
        DoubleIntegral(
            lowerX = -sqrt(PI), upperX = 0.0,
            lowerY = 0.0, upperY = PI,
            func =  { x, y -> ( x * cos(x.pow(2) + y) ) }
        )
    )

    val tripleIntegral = TripleIntegral(
        lowerX = 0.0,
        upperX = PI,
        lowerY = 0.0,
        upperY = PI,
        lowerZ = 0.0,
        upperZ = PI,
        func = { x, y, z -> ( cos(x+y+z) ) }
    )
}