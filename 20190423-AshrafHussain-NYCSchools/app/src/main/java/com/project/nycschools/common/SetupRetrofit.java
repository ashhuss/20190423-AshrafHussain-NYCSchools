package com.project.nycschools.common;


import com.project.nycschools.data.AppConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SetupRetrofit {

    public  Retrofit getRetrofit(){
         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(AppConstant.BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
         return retrofit;
    }

}
