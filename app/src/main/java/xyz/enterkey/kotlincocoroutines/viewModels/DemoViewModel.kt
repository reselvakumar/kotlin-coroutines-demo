package xyz.enterkey.kotlincocoroutines.viewModels

import android.util.Log
import androidx.annotation.UiThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xyz.enterkey.kotlincocoroutines.livedata.UILoader
import xyz.enterkey.kotlincocoroutines.models.PostResponseModel
import xyz.enterkey.kotlincocoroutines.models.UserResponseModel
import xyz.enterkey.kotlincocoroutines.repositories.ApiFactory
import xyz.enterkey.kotlincocoroutines.repositories.DemoRepository
import xyz.enterkey.kotlincocoroutines.repositories.models.CallResponseStatus
import xyz.enterkey.kotlincocoroutines.utils.Coroutines

class DemoViewModel : ViewModel() {

    private val repository = DemoRepository(ApiFactory.apiService)

    val userResult = MutableLiveData<UILoader<List<UserResponseModel>>>()
    val postResult = MutableLiveData<UILoader<List<PostResponseModel>>>()

    @UiThread
    fun users() {
        // _loading.value = true
        Coroutines.ioThenMain({
            repository.getUser()
        }) {
            it?.let {
                Log.d("data", it.toString())
                userResult.value = UILoader.loading()
                val resultData = it
                if (resultData.status == CallResponseStatus.SUCCESS) {
                    if(resultData.data.isNullOrEmpty()){
                        userResult.value = UILoader.error("Something wen't wrong")
                    }else{
                        userResult.value = UILoader.success(resultData.data)
                    }
                } else {
                    userResult.value = UILoader.error(resultData.message)
                }
            }
        }
    }

    @UiThread
    fun posts(userId:String) {
        // _loading.value = true
        Coroutines.ioThenMain({
            repository.getPost(userId)
        }) {
            it?.let {
                Log.d("data", it.toString())
                postResult.value = UILoader.loading()
                val resultData = it
                if (resultData.status == CallResponseStatus.SUCCESS) {
                    if(resultData.data.isNullOrEmpty()){
                       postResult.value = UILoader.error("Something wen't wrong")
                    }else{
                        postResult.value = UILoader.success(resultData.data)
                    }
                } else {
                    postResult.value = UILoader.error(resultData.message)
                }
            }
        }
    }


}