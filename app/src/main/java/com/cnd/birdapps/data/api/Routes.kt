package com.cnd.birdapps.data.api

import com.cnd.birdapps.data.model.article.ArticleResponse
import com.cnd.birdapps.data.model.user.get.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


/**
 ** Written by CND_Studio 13/03/2021 23.44.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/
interface Routes {
    @GET("user")
    fun apiGetUser(
        @Query("id") type: String
    ): Call<UserResponse>

    @GET("article")
    fun apiGetArticle(): Call<ArticleResponse>

    @Multipart
    @POST("article")
    fun apiPostArticle(
        @Part("userId") userId: RequestBody,
        @Part("birdSpeciesId") birdSpeciesId: RequestBody,
        @Part("description") description: RequestBody,
        @Part image : MultipartBody.Part
//        @Part("image\"; filename=\"myfile.jpg\" ") file: RequestBody?
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("user")
    fun apiPostUser(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseBody>
}