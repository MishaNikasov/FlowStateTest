package com.lampa.flowstatetest.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.lampa.flowstatetest.databinding.ItemPostBinding
import com.lampa.flowstatetest.network.model.PostResponseItem

class PostAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var interaction: Interaction? = null

    private val callback = object : DiffUtil.ItemCallback<PostResponseItem>() {

        override fun areItemsTheSame(
            oldItem: PostResponseItem,
            newItem: PostResponseItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PostResponseItem,
            newItem: PostResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, callback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return PostViewHolder(
            ItemPostBinding.inflate((LayoutInflater.from(parent.context)), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<PostResponseItem?>?) {
        differ.submitList(list)
    }

    inner class PostViewHolder constructor(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PostResponseItem) = with(itemView) {
            binding.post = item
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            binding.executePendingBindings()
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: PostResponseItem)
    }
}
