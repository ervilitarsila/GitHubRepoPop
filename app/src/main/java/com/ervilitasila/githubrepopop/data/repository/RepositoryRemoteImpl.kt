package com.ervilitasila.githubrepopop.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ervilitasila.githubrepopop.data.model.PullRequest
import com.ervilitasila.githubrepopop.data.model.Repository
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

    override fun listRepositories(page: Int): LiveData<List<Repository>> {
        val data = MutableLiveData<List<Repository>>()

        val disposableObserver = remoteDataSource.listRepositories(page)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<Repository>>() {

                override fun onNext(repositories: List<Repository>) {
                    data.postValue(repositories)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                }

            })

        compositeDisposable.add(disposableObserver)

        return data
    }

    override fun getOwner(ownerName: String): LiveData<User> {
        val data = MutableLiveData<User>()

        val disposableObserver = remoteDataSource.getOwner(ownerName)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<User>() {

                override fun onNext(user: User) {
                    data.postValue(user)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
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

        val disposableObserver = remoteDataSource.listPullRequests(ownerName, repositoryName)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<PullRequest>>() {

                override fun onNext(pullRequests: List<PullRequest>) {
                    data.postValue(pullRequests)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                }

            })

        compositeDisposable.add(disposableObserver)

        return data
    }

}