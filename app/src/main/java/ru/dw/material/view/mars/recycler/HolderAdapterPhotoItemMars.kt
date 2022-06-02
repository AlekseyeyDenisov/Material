package ru.dw.material.view.mars.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.dw.material.BuildConfig
import ru.dw.material.MyApp
import ru.dw.material.R
import ru.dw.material.databinding.ItemPhotoBinding
import ru.dw.material.dto.PhotosItem
import ru.dw.material.dto.ResponseEarth
import ru.dw.material.dto.ResponseMars
import ru.dw.material.repository.PictureOfTheDayRetrofitImpl.NASA_BASE_URL

class HolderAdapterPhotoItemMars(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: PhotosItem) {
        ItemPhotoBinding.bind(itemView).apply {
            photoItem.load(item.imgSrc) { placeholder(R.drawable.loadig) }
        }

    }

}