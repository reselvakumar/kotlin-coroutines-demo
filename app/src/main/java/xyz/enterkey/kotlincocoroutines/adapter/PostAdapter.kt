package xyz.enterkey.kotlincocoroutines.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import xyz.enterkey.kotlincocoroutines.R
import xyz.enterkey.kotlincocoroutines.models.PostResponseModel
import xyz.enterkey.kotlincocoroutines.models.UserResponseModel


class PostAdapter(private val postData:List<PostResponseModel>) : RecyclerView.Adapter<PostAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return Holder(itemView)
    }

    override fun getItemCount(): Int {
        return postData.size
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view){

        private val title: AppCompatTextView =itemView.findViewById(R.id.title)
        private val description: AppCompatTextView =itemView.findViewById(R.id.description)


        fun bind(pos: Int) {
            val dataBean = postData[pos]

            title.text=dataBean.title

            description.text=dataBean.body



        }


    }
}