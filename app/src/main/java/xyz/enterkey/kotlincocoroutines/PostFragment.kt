package xyz.enterkey.kotlincocoroutines


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.fragment_post.*
import xyz.enterkey.kotlincocoroutines.adapter.PostAdapter
import xyz.enterkey.kotlincocoroutines.adapter.UserAdapter
import xyz.enterkey.kotlincocoroutines.livedata.UILoaderStatus
import xyz.enterkey.kotlincocoroutines.models.PostResponseModel
import xyz.enterkey.kotlincocoroutines.models.UserResponseModel
import xyz.enterkey.kotlincocoroutines.utils.getViewModel
import xyz.enterkey.kotlincocoroutines.viewModels.DemoViewModel

class PostFragment : Fragment() {

    private val vm: DemoViewModel by lazy {
        getViewModel{ DemoViewModel() }
    }

    private val args: PostFragmentArgs by navArgs()

    private var userData:UserResponseModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        args.userData
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<UserResponseModel> =
            moshi.adapter(UserResponseModel::class.java)

        userData = jsonAdapter.fromJson(args.userData)
        if(userData==null){
            Toast.makeText(context,"Something wen't wrong", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.posts(userData?.id.toString())
        vm.postResult.observe(viewLifecycleOwner, Observer {result->
            showProgress(false)
            when(result.status){
                UILoaderStatus.SUCCESS->{
                    recycler.adapter= PostAdapter(result.data as List<PostResponseModel>)
                    recycler.adapter?.notifyDataSetChanged()
                }
                UILoaderStatus.LOADING->{
                    showProgress(true)
                }
                UILoaderStatus.ERROR->{
                    Toast.makeText(context,result.message,Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun showProgress(visibility:Boolean){
        progressBar.visibility = if(visibility){View.VISIBLE}else{View.GONE}
    }


}
