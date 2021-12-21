package com.codemul.pabmul.helloworld

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.Event
import com.codemul.pabmul.helloworld.data.Quest
import com.codemul.pabmul.helloworld.data.Scrim

class QuestAdapter(private val context: Context, private val content: MutableList<Quest>) :
    RecyclerView.Adapter<QuestAdapter.QuestViewHolder>() {

    private var onItemClickCallback: QuestAdapter.OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: QuestAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(quest: Quest)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): QuestAdapter.QuestViewHolder {
        return QuestViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.quest_content, parent, false))
    }

    override fun onBindViewHolder(holder: QuestAdapter.QuestViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return content.size
    }

    inner class QuestViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

//        var questImage: ImageView
        var questName: TextView
        var questDesc: TextView
        var questReward: TextView
        var progressQuest: ProgressBar
        var pickButton: Button

        init {
//            questImage = itemView.findViewById(R.id.iv_quest_image)
            questName = itemView.findViewById(R.id.tv_quest_title)
            questDesc = itemView.findViewById(R.id.tv_desc_quest)
            questReward = itemView.findViewById(R.id.tv_reward_quest)
            progressQuest = itemView.findViewById(R.id.pb_quest_prog)
            pickButton = itemView.findViewById(R.id.btn_pilih_quest)
        }


        internal fun bind(position: Int) {
            questName.setText(content[position].namaQuest)
            questDesc.setText(content[position].questDesc)
            questReward.setText(content[position].reward)
            pickButton.setOnClickListener { onItemClickCallback?.onItemClicked(content[position])}
        }
    }
}