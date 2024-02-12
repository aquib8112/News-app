package com.example.newsapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.ArticleDetails

class HomeAdapter : ListAdapter<ArticleDetails, HomeAdapter.TaskViewHolder>(DiffUtil()) {

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ArticleDetails>() {
        override fun areItemsTheSame(oldItem: ArticleDetails, newItem: ArticleDetails): Boolean {
            return oldItem.url == newItem.url // Assuming URL is unique for each article
        }

        override fun areContentsTheSame(oldItem: ArticleDetails, newItem: ArticleDetails): Boolean {
            return oldItem == newItem // If URL is unique, this is sufficient
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false) // Use appropriate layout
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val articleDetails = getItem(position)
        holder.bind(articleDetails)
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var title = view.findViewById<TextView>(R.id.article_title)
        private val image = view.findViewById<ImageView>(R.id.article_image)

        fun bind(articleDetails: ArticleDetails) {
            title.text = articleDetails.title
            if (articleDetails.imageUrl.isNullOrEmpty()) {
                image.setImageResource(R.drawable.icons8_newspaper_48)
                image.scaleX = 0.5f
                image.scaleY = 0.5f
            } else {
                Glide.with(itemView.context)
                    .load(articleDetails.imageUrl)
                    .into(image)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, WebActivity::class.java)
                val articleUrl = articleDetails.url
                intent.putExtra("url",articleUrl)
                itemView.context.startActivity(intent)
            }
        }
    }
}