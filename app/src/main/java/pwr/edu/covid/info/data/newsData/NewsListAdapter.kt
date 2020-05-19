package pwr.edu.covid.info.data.newsData

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewsListAdapter(private val onItemClick: (news: NewsEntity) -> Unit)
    : RecyclerView.Adapter<NewsEntityViewHolder>(){

    private val newsList = mutableListOf<NewsEntity>()

    fun addItems(news: List<NewsEntity>) {
        newsList.addAll(news)
        notifyItemRangeChanged(itemCount, newsList.size)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsEntityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsEntityViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: NewsEntityViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
        holder.itemView.setOnClickListener {
            onItemClick(news)
        }
    }

    override fun getItemCount(): Int = newsList.size
}