package com.flores.joseph.laboratoriocalificado03

data class ProfesorResponse(
    val name: String,
    val last_name: String,
    val phone_number: String,
    val email: String,
    val image_url: String
){
//    fun getProfesorEmail(): String = email
//
    fun getProfesorImage(): String = image_url
//
}

