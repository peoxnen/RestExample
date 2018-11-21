package iview.wsienski.restexample.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import iview.wsienski.restexample.databinding.ListItemBinding


/**
 * Created by Witold Sienski on 21.11.2018.
 */
class UserAdapter : ListAdapter<UserView, UserAdapter.MyViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ListItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(getItem(position))

    inner class MyViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserView) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}