package pwr.edu.covid.info.data

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import pwr.edu.covid.info.R
import pwr.edu.covid.info.network.ServiceStatus
import timber.log.Timber

/**
 * For the spinner visibility
 */
@BindingAdapter("spinnerVisibility")
fun spinnerVisibility(progressBar: ProgressBar, status: ServiceStatus?) {
    progressBar.visibility = when (status) {
        ServiceStatus.WAITING -> View.VISIBLE
        ServiceStatus.ERROR -> View.INVISIBLE
        ServiceStatus.DONE -> View.GONE
        else -> View.GONE
    }
}

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url != null)
        Glide.with(imageView.context).load(url).into(imageView)
    else {
        Timber.w("Null encodedString for $imageView")
        imageView.setImageResource(R.drawable.ic_news)
    }
}