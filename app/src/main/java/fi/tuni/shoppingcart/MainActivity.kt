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
import java.text.SimpleDateFormat

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

        retroFitData.enqueue(object : Callback<List<items>?> {
            override fun onResponse(
                call: Call<List<items>?>,
                response: Response<List<items>?>
            ) {
                val responseBody = response.body()
                var myStringBuilder = StringBuilder()
                val DateFor = SimpleDateFormat("dd/MM/yyyy")
                if(responseBody != null) {
                    for(data in responseBody) {
                        myStringBuilder.append(data.item)
                        myStringBuilder.append(" ")
                        val date = DateFor.format(data.due_date)
                        myStringBuilder.append(date)

                        myStringBuilder.append("\n")
                    }

                    listview.text = myStringBuilder

                }
            }

            override fun onFailure(call: Call<List<items>?>, t: Throwable) {
                listview.text = t.message

            }
        })
    }


}