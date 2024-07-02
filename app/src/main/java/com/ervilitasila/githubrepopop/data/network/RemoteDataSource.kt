package com.ervilitasila.githubrepopop.data.network

import com.ervilitasila.githubrepopop.data.model.PullRequest
import com.ervilitasila.githubrepopop.data.model.Repository
import com.ervilitasila.githubrepopop.data.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteDataSource {

    fun listRepositories(page: Int): Observable<List<Repository>> = RetrofitClient.gitHubApi
        .listRepositories(page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getOwner(ownerName: String): Observable<User> = RetrofitClient.gitHubApi
        .getOwner(ownerName)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun listPullRequests(ownerName: String, repositoryName: String): Observable<List<PullRequest>> = RetrofitClient.gitHubApi
        .listPullRequests(ownerName, repositoryName)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}