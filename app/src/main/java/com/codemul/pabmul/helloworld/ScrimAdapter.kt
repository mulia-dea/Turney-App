package com.codemul.pabmul.helloworld

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ScrimAdapter : RecyclerView.Adapter<ScrimAdapter.ScrimViewHolder>(){
    inner class ScrimViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ScrimAdapter.ScrimViewHolder {
        return ScrimViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_scrim, parent, false))
    }

    override fun onBindViewHolder(holder: ScrimAdapter.ScrimViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}