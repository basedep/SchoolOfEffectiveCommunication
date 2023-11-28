package projects.school.communication.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item.view.*
import projects.school.communication.R
import projects.school.communication.model.Course
import projects.school.communication.utils.MyDiffUtil

class CourseRecyclerAdapter() : RecyclerView.Adapter<CourseRecyclerAdapter.ChildViewHolder>() {

    val differ = AsyncListDiffer(this, MyDiffUtil)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {

        //inflate child view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)

        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {

        val course = differ.currentList[position]

        holder.itemView.apply {
            Picasso.with(holder.itemView.context)
                .load(course.imageURL)
                .placeholder(R.drawable.ic_image_placeholder_24)
                .fit()
                .centerCrop()
                .into(image_view)
            header.text = course.header
            progressBar.progress = course.progress
            setOnClickListener {
                onItemClickListener?.let{ it(course) }
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private var onItemClickListener: ((Course) -> Unit)? = null

    fun setOnItemClickListener(listener: (Course) -> Unit){
        onItemClickListener = listener
    }

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}