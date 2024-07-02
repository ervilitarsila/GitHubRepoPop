package com.ervilitasila.githubrepopop.data.network

import com.ervilitasila.githubrepopop.data.model.PullRequest
import com.ervilitasila.githubrepopop.data.model.Repository
import com.ervilitasila.githubrepopop.data.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("/search/repositories?q=language:Kotlin&sort=stars&page={page}")
    fun listRepositories(
        @Path("page") page: Int
    ): Observable<List<Repository>>

    @GET("/user/{ownerName}")
    fun getOwner(
        @Path("ownerName") ownerName: String
    ): Observable<User>

    @GET("/repos/{ownerName}/{repositoryName}/pulls")
    fun listPullRequests(
        @Path("ownerName") ownerName: String,
        @Path("repositoryName") repositoryName: String
    ): Observable<List<PullRequest>>
}