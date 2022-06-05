package ru.dw.material.view.nasa.mars.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.dw.material.R
import ru.dw.material.databinding.ItemPhotoBinding
import ru.dw.material.dto.PhotosItem

class HolderAdapterPhotoItemMars(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: PhotosItem) {
        ItemPhotoBinding.bind(itemView).apply {
            photoItem.load(item.imgSrc) { placeholder(R.drawable.loadig) }
        }

    }

}