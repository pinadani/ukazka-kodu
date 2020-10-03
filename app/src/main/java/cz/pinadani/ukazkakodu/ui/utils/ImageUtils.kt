package cz.pinadani.ukazkakodu.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageUtils {
}

@BindingAdapter("roundImageUrl")
fun setRoundImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .circleCrop()
        .into(imageView)
}