package uz.shokirov.authorizationretrofit2.model

data class LoginModel(
    val access_token: String,
    val role: String
)