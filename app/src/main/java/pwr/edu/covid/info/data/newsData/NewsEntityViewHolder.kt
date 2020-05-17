package pwr.edu.covid.info.data.newsData

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pwr.edu.covid.info.R

class NewsEntityViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.cardview_item, parent, false)) {
    private var tvNewsTitle: TextView? = null
    private var tvNewsDesc: TextView? = null

    init {
        tvNewsTitle = itemView.findViewById(R.id.textTitle)
        tvNewsDesc = itemView.findViewById(R.id.textDesc)
    }

    fun bind(newsEntity: NewsEntity) {
        tvNewsTitle?.text = newsEntity.title
        tvNewsDesc?.text = newsEntity.excerpt
    }
}