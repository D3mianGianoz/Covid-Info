package pwr.edu.covid.info.NewsData

import NewsEntity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pwr.edu.covid.info.R

class NewsEntityViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.cardview_item, parent, false)) {
    private var tv_news_title: TextView? = null
    private var tv_news_desc: TextView? = null


    init {
        tv_news_title = itemView.findViewById(R.id.textTitle)
        tv_news_desc = itemView.findViewById(R.id.textDesc)
    }

    fun bind(newsEntity: NewsEntity) {
        tv_news_title?.text = newsEntity.title
        tv_news_desc?.text = newsEntity.excerpt
    }

}