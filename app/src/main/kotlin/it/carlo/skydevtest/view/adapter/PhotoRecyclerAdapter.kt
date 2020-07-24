package it.carlo.skydevtest.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.carlo.skydevtest.R
import it.carlo.skydevtest.model.data.RedditPhotoModelView
import it.carlo.skydevtest.utils.ImageCache
import it.carlo.skydevtest.utils.setHtmlText

class PhotoRecyclerAdapter(private val items:ArrayList<RedditPhotoModelView>, private val onPhotoItemClick: (RedditPhotoModelView) -> Unit?): RecyclerView.Adapter<PhotoRecyclerAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(items[position], onPhotoItemClick)
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemPhotoImageView = itemView.findViewById<ImageView>(R.id.itemPhotoImage)
        private val itemPhotoTitleTextView = itemView.findViewById<TextView>(R.id.itemPhotoTitle)
        private val itemPhotoAuthorTextView = itemView.findViewById<TextView>(R.id.itemPhotoAuthor)

        fun bind(item: RedditPhotoModelView, listener: (RedditPhotoModelView) -> Unit?) {
            ImageCache.loadImage(itemPhotoImageView, item.thumbnailUrl, R.drawable.item_photo_placeholder)
            itemPhotoTitleTextView.setHtmlText(item.title)
            itemPhotoAuthorTextView.text = item.author

            itemView.setOnClickListener { listener.invoke(item) }
        }
    }

}