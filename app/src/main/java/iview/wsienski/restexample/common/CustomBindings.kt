package iview.wsienski.restexample.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/**
 * Created by Witold Sienski on 21.11.2018.
 */
@BindingAdapter("bind:imageUrl")
fun setImageUrl(view: ImageView, url: String) {
    val options = RequestOptions()
        .placeholder(android.R.drawable.btn_star)

    Glide.with(view.context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(view)
}