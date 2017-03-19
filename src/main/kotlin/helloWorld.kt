/**
 * REST API for getting tokens
 * Created by juanma on 15/3/17.
 */

import spark.Spark.get
import spark.Spark.post
import spark.Spark.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun main(args: Array<String>) {
    get("/hello") { req, res -> "Hello World" }
    path("/tokens") {
        get("") { req, res ->
            createToken()
        }
        // TODO check this instead, so I can get the params from the body
        // http://stackoverflow.com/a/30113863/3923525
        post("") { req, res ->
            val token = req.queryParams("token")
            val verifiedToken = retrieveToken(token)
            res.type("application/json")
            jacksonObjectMapper().writeValueAsString(verifiedToken)
        }
    }
}

fun createToken(): String {
    val jwtProvider: JwtProvider = JwtProvider()
    val jwt: String = jwtProvider.createJwt()
    return jwt
}

fun retrieveToken(jwt: String): String {
    val jwtProvider: JwtProvider = JwtProvider()
    return jwtProvider.verifyJwt(jwt)
}

fun getSignatureKey(): ByteArray {
    return byteArrayOf(10, 20, 30, 40, 50, 60)
}
