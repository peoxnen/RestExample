package iview.wsienski.restexample.ui.main

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Witold Sienski on 21.11.2018.
 */
class UserDiffCallback : DiffUtil.ItemCallback<UserView>() {
    override fun areItemsTheSame(oldItem: UserView, newItem: UserView): Boolean =
        oldItem.login == newItem.login

    override fun areContentsTheSame(oldItem: UserView, newItem: UserView): Boolean =
        oldItem == newItem
}