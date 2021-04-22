package com.cnd.birdapps.data.api

import com.cnd.birdapps.data.model.article.ArticleResponse
import com.cnd.birdapps.data.model.kategory.KategoryResponse
import com.cnd.birdapps.data.model.url3d.Url3dResponse
import com.cnd.birdapps.data.model.user.UserResponse
import com.cnd.birdapps.data.model.userLogin.UserLogResponse
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

    @GET("birdSpecies")
    fun apiGetBirdSpecies(): Call<KategoryResponse>

    @GET("3d")
    fun apiGet3dUrl(): Call<Url3dResponse>

    @GET("article")
    fun apiGetAllArticle(): Call<ArticleResponse>

    @GET("article")
    fun apiGetArticle(
        @Query("publish") publish: Boolean
    ): Call<ArticleResponse>

    @GET("article")
    fun apiGetArticleQuery(
        @Query("birdSpeciesId") birdSpeciesId: Int,
        @Query("publish") publish: Boolean
    ): Call<ArticleResponse>

    @GET("article")
    fun apiGetArticleUserId(
        @Query("userId") userId: Int
    ): Call<ArticleResponse>

    @FormUrlEncoded
    @PUT("article/{id}")
    fun apiPutArticle(
        @Path("id") id: String,
        @Field("publish") publish: Boolean,
    ): Call<ResponseBody>

    @DELETE("article/{id}")
    fun deleteArticle(@Path("id") id: Int): Call<ResponseBody>

    @Multipart
    @POST("article")
    fun apiPostArticle(
        @Part("userId") userId: RequestBody,
        @Part("birdSpeciesId") birdSpeciesId: RequestBody,
        @Part("description") description: RequestBody,
        @Part image: MultipartBody.Part
//        @Part("image\"; filename=\"myfile.jpg\" ") file: RequestBody?
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("user")
    fun apiPostUser(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("user/login")
    fun apiPostLoginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<UserLogResponse>

}