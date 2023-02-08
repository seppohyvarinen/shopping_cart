package fi.tuni.shoppingcart

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

lateinit var header : TextView
lateinit var listview : TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        setContentView(R.layout.activity_main)
        header = findViewById(R.id.header)
        listview = findViewById(R.id.listID)
        getShoppingCart()

    }

    private fun getShoppingCart() {
        val retroFitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://shopping-cart-api-qaez.onrender.com/")
            .build()
            .create(CartAPI::class.java)

        val retroFitData = retroFitBuilder.getList()

        retroFitData.enqueue(object : Callback<List<Shoppingcart>?> {
            override fun onResponse(
                call: Call<List<Shoppingcart>?>,
                response: Response<List<Shoppingcart>?>
            ) {
                val responseBody = response.body()
                var myStringBuilder = StringBuilder()
                if(responseBody != null) {
                    for(data in responseBody) {
                        myStringBuilder.append(data.message)
                        myStringBuilder.append("\n")
                        myStringBuilder.append(data.message2)
                    }

                    listview.text = myStringBuilder

                }
            }

            override fun onFailure(call: Call<List<Shoppingcart>?>, t: Throwable) {
                listview.text = t.message

            }
        })
    }


}