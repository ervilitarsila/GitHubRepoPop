package com.ervilitasila.githubrepopop.data.repository

import Repository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ervilitasila.githubrepopop.data.model.PullRequest
import com.ervilitasila.githubrepopop.data.model.User
import com.ervilitasila.githubrepopop.data.network.RemoteDataSource
import dagger.Module
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Module
class RepositoryRemoteImpl @Inject constructor(): RepositoryRemote {

    private val compositeDisposable = CompositeDisposable()
    private val remoteDataSource = RemoteDataSource()

    override fun listRepositories(page: String): LiveData<List<Repository>> {
        val data = MutableLiveData<List<Repository>>()

        val disposableObserver = remoteDataSource.listRepositories(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<Repository>>() {

                override fun onNext(repositories: List<Repository>) {
                    data.postValue(repositories)
                    Log.d("RepositoryRemoteImpl", "Repositories: $repositories")
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.e("RepositoryRemoteImpl", "Error: ${e.message}")
                }

                override fun onComplete() {
                    Log.d("RepositoryRemoteImpl", "listRepositories onComplete")
                }

            })

        compositeDisposable.add(disposableObserver)
        return data
    }

    override fun getOwner(ownerName: String): LiveData<User> {
        val data = MutableLiveData<User>()
        Log.d("RepositoryRemoteImpl", "getOwner called")

        val disposableObserver = remoteDataSource.getOwner(ownerName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<User>() {

                override fun onNext(user: User) {
                    data.postValue(user)
                    Log.d("RepositoryRemoteImpl", "User: $user")
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.e("RepositoryRemoteImpl", "Error: ${e.message}")
                }

                override fun onComplete() {
                    Log.d("RepositoryRemoteImpl", "getOwner onComplete")
                }

            })

        compositeDisposable.add(disposableObserver)
        return data
    }

    override fun listPullRequest(
        ownerName: String,
        repositoryName: String
    ): LiveData<List<PullRequest>> {
        val data = MutableLiveData<List<PullRequest>>()
        Log.d("RepositoryRemoteImpl", "listPullRequest called")

        val disposableObserver = remoteDataSource.listPullRequests(ownerName, repositoryName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<PullRequest>>() {

                override fun onNext(pullRequests: List<PullRequest>) {
                    data.postValue(pullRequests)
                    Log.d("RepositoryRemoteImpl", "PullRequests: $pullRequests")
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.e("RepositoryRemoteImpl", "Error: ${e.message}")
                }

                override fun onComplete() {
                    Log.d("RepositoryRemoteImpl", "listPullRequest onComplete")
                }

            })

        compositeDisposable.add(disposableObserver)
        return data
    }

}