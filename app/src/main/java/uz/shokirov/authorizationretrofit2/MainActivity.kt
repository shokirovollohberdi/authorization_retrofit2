package uz.shokirov.authorizationretrofit2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.shokirov.authorizationretrofit2.client.RetrofitClient
import uz.shokirov.authorizationretrofit2.data.DataSources
import uz.shokirov.authorizationretrofit2.model.LoginModel

var token = ""

class MainActivity : AppCompatActivity() {
    lateinit var edtLogin: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    val dataSources  = DataSources()
    private  val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtLogin = findViewById(R.id.edtLogin)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)

        checkUser()

        btnLogin.setOnClickListener {
            register()
        }
        }

    private fun checkUser(){
        if (dataSources.isLogin(this)){
            startActivity(Intent(this@MainActivity,MainActivity2::class.java))
            token = dataSources.getToken(this)
        }
    }

    private fun register() {
        RetrofitClient.getRetrofit().checkUser("${edtLogin.text.trim()}","${edtPassword.text.trim()}").enqueue(object :
            Callback<LoginModel> {
            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                Log.d(TAG, "onResponse: ${response.code()}")
                if (response.code() == 200){
                    Toast.makeText(this@MainActivity, "True", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@MainActivity,MainActivity2::class.java))
                    dataSources.setLogin(this@MainActivity,true)
                    dataSources.saveToken(this@MainActivity,response.body()!!.access_token)
                    dataSources.saveRole(this@MainActivity,response.body()!!.role)
                }
                else{
                    Toast.makeText(this@MainActivity, "false", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
                Toast.makeText(this@MainActivity, "False failure", Toast.LENGTH_SHORT).show()
            }
        })

    }
}