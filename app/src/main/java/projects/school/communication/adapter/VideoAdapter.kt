package projects.school.communication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_video.view.pass_test
import kotlinx.android.synthetic.main.recycler_item_video.view.title
import kotlinx.android.synthetic.main.recycler_item_video.view.watch_video
import projects.school.communication.R
import projects.school.communication.model.Tests
import projects.school.communication.model.Video

class VideoAdapter(private val videoList: List<Video>, private val testList:List<Tests>) : RecyclerView.Adapter<VideoAdapter.VideoHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_video, parent, false)

        return VideoHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {

        val video = videoList[position]
        val test = testList[position]

        holder.itemView.apply {
            title.text = video.title
            watch_video.setOnClickListener{
                onItemClickListener?.let{it(video)}
            }

            pass_test.setOnClickListener {
                onTestItemClickListener?.let { it(test) }
            }
        }
    }


    private var onItemClickListener: ((Video) -> Unit)? = null

    fun setOnItemClickListener(listener: (Video) -> Unit){
        onItemClickListener = listener
    }

    private var onTestItemClickListener: ((Tests) -> Unit)? = null

    fun setOnTestItemClickListener(listener: (Tests) -> Unit){
        onTestItemClickListener = listener
    }

    inner class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }


}