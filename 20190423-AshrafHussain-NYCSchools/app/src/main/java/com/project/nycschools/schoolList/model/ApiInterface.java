package com.project.nycschools.schoolList.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("f9bf-2cp4.json")
    Call<List<SchoolModel>> getSchoolData();
}
