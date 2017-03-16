/**
 * Created by juanma on 15/3/17.
 */

import spark.Spark.*

fun main(args: Array<String>) {
    get("/hello") { req, res -> "Hello World" }
}
