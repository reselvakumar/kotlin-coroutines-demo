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
import xyz.enterkey.kotlincocoroutines.models.UserResponseModel


class UserAdapter(private val userData:List<UserResponseModel>, val adapterListener: OnUserAdapterListener?) : RecyclerView.Adapter<UserAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return Holder(itemView)
    }

    override fun getItemCount(): Int {
        return userData.size
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val name: AppCompatTextView =itemView.findViewById(R.id.name)
        private val email: AppCompatTextView =itemView.findViewById(R.id.email)
        private val website: AppCompatTextView =itemView.findViewById(R.id.website)
        private val parent: LinearLayout =itemView.findViewById(R.id.parent)

        init {
            parent.setOnClickListener(this)
        }

        fun bind(pos: Int) {
            val dataBean = userData[pos]

            name.text=dataBean.name

            email.text=dataBean.email

            website.text=dataBean.website

        }

        override fun onClick(view: View) {
            when (view.id) {
                R.id.parent -> {
                    adapterListener?.onItemClick(userData[adapterPosition])
                }
            }
        }

    }
}