package com.ervilitasila.githubrepopop.data.network

import com.ervilitasila.githubrepopop.data.model.PullRequest
import com.ervilitasila.githubrepopop.data.model.RepositoryResponse
import com.ervilitasila.githubrepopop.data.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("/search/repositories")
    fun listRepositories(
        @Query("q") query: String = "language:Kotlin",
        @Query("sort") sort: String = "stars",
        @Query("page") page: String
    ): Observable<RepositoryResponse>

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