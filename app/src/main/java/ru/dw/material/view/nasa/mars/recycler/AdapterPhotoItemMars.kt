package ru.dw.material.view.nasa.mars.recycler


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.dw.material.databinding.ItemPhotoBinding
import ru.dw.material.dto.PhotosItem


class AdapterPhotoItemMars():
    ListAdapter<PhotosItem, HolderAdapterPhotoItemMars>(PhotoItemMarsDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderAdapterPhotoItemMars {
        val binding =
            ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderAdapterPhotoItemMars(binding.root)
    }

    override fun onBindViewHolder(holder: HolderAdapterPhotoItemMars, position: Int) {
        holder.bind(getItem(position))
    }

}