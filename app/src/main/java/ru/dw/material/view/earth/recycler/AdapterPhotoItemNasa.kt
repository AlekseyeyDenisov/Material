package ru.dw.material.view.earth.recycler


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.dw.material.databinding.ItemPhotoBinding
import ru.dw.material.dto.ResponseEarth



class AdapterPhotoItemNasa():
    ListAdapter<ResponseEarth, HolderAdapterPhotoItem>(PhotoItemDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderAdapterPhotoItem {
        val binding =
            ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderAdapterPhotoItem(binding.root)
    }

    override fun onBindViewHolder(holder: HolderAdapterPhotoItem, position: Int) {
        holder.bind(getItem(position))
    }

}