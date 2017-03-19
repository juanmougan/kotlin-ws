/**
 * Created by juanma on 15/3/17.
 */

import spark.Spark.get
import spark.Spark.post

fun main(args: Array<String>) {
    get("/hello") { req, res -> "Hello World" }
    get("/token") { req, res ->
        createToken()
    }
    post("/token") { req, res ->
        val token = req.queryParams("token")
        retrieveToken(token)
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
