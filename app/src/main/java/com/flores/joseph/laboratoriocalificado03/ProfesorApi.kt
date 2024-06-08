package com.flores.joseph.laboratoriocalificado03

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ProfesorApi {
    @Headers("Content-Type: application/json")
    @GET("/list/teacher-b")
    suspend fun getProfesores(): Response<ProfesorListResponse>

}