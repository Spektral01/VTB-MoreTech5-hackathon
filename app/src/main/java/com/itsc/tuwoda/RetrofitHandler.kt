/*
package com.itsc.tuwoda.Model

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHandler {

    //private val BASE_URL = "https://26.113.35.139:8080/api/offices/get/"

    public fun getAllOffice(): MutableList<OfficeModel>{

        var officeList: MutableList<OfficeModel> = mutableListOf()

        val api= Retrofit.Builder()
            .baseUrl("https://26.113.35.139:8080/api/offices/get/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitAPI::class.java)

        api.getOffice().enqueue(object : Callback<List<OfficeModel>> {
            override fun onResponse(
                call: Call<List<OfficeModel>>,
                response: Response<List<OfficeModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let{
                        for(comm in it){
                            Log.i("Retro", comm.address)
                            officeList.add(comm)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<OfficeModel>>, t: Throwable) {
                Log.i("Retro", "ERRR")

            }
        })
        return officeList


    }

    public fun getAllATM(): MutableList<ATMModel>{

        var ATMList: MutableList<ATMModel> = mutableListOf()

        val api= Retrofit.Builder()
            .baseUrl("https://26.113.35.139:8080/api/atm/get/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitAPI::class.java)

        api.getATM().enqueue(object : Callback<List<ATMModel>> {
            override fun onResponse(
                call: Call<List<ATMModel>>,
                response: Response<List<ATMModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let{
                        for(comm in it){
                            Log.i("Retro", comm.address)
                            ATMList.add(comm)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<ATMModel>>, t: Throwable) {
                Log.i("Retro", "ERRR")

            }
        })
        return ATMList


    }
}
*/
