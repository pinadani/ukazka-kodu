package cz.pinadani.ukazkakodu.ui.utils

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageUtils {
}

@BindingAdapter("roundImageUrl")
fun setRoundImage(imageView: ImageView, url: String?) {
    if(!TextUtils.isEmpty(url)) {
        Glide.with(imageView.context)
            .load(url)
            .circleCrop()
            .into(imageView)
    }
}