package pwr.edu.covid.info.data.newsData

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pwr.edu.covid.info.databinding.NewsCardviewItemBinding

class NewsAdapter(private val clickListener: NewsListener) :
    ListAdapter<NewsItem, NewsAdapter.NewsHolder>(NewsDiffCallback()) {


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NewsHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val item: NewsCardviewItemBinding = NewsCardviewItemBinding.inflate(layoutInflater, parent, false)

        return NewsHolder(item)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val news = getItem(position)
        holder.bind(news, clickListener)
    }

    class NewsHolder(listItemNewsBinding: NewsCardviewItemBinding) :
        RecyclerView.ViewHolder(listItemNewsBinding.root) {

        // If your layout file is something_awesome.xml then your binding class will be SomethingAwesomeBinding
        // Since our layout file is item_movie.xml, our auto generated binding class is ItemMovieBinding
        private var binding: NewsCardviewItemBinding = listItemNewsBinding

        /**
         * We will use this function to bind instance of NewsItem to the row
         */
        fun bind(newsObj: NewsItem, clickListener: NewsListener) {
            binding.newsItem = newsObj
            binding.clickListener = clickListener
//            binding.executePendingBindings()
        }
    }
}

class NewsListener(val clickListener: (news: NewsItem) -> Unit) {
    fun onClick(news: NewsItem) = clickListener(news)
}

class NewsDiffCallback :
    DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }
}