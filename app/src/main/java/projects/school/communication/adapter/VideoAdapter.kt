package projects.school.communication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_video.view.title
import kotlinx.android.synthetic.main.recycler_item_video.view.watch_video
import projects.school.communication.R
import projects.school.communication.model.Course
import projects.school.communication.model.Video

class VideoAdapter(private val videoList: List<Video>) : RecyclerView.Adapter<VideoAdapter.VideoVideHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoVideHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_video, parent, false)

        return VideoVideHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoVideHolder, position: Int) {

        val video = videoList[position]

        holder.itemView.apply {
            title.text = video.title
            watch_video.setOnClickListener{
                onItemClickListener?.let{it(video)}
            }
        }
    }


    private var onItemClickListener: ((Video) -> Unit)? = null

    fun setOnItemClickListener(listener: (Video) -> Unit){
        onItemClickListener = listener
    }

    inner class VideoVideHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }


}