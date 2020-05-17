package pwr.edu.covid.info.NewsData

import NewsEntity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewsListAdapter(private val list: List<NewsEntity>)
    : RecyclerView.Adapter<NewsEntityViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsEntityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsEntityViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: NewsEntityViewHolder, position: Int) {
        val newsEntity: NewsEntity = list[position]
        holder.bind(newsEntity)
    }

    override fun getItemCount(): Int = list.size
}