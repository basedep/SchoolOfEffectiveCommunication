package projects.school.comunication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import projects.school.comunication.R
import projects.school.comunication.model.Course

class SearchRecyclerAdapter(private val list: List<Course>) : RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_recycler_item, parent, false)

        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.apply {
            headerText.text = list[position].header
            image.setImageDrawable(ResourcesCompat.getDrawable(holder.image.context.resources, list[position].image, null)) //to delete
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }



    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val image: ImageView = view.findViewById(R.id.search_image_list_item)
        val headerText: TextView = view.findViewById(R.id.search_list_title)
        val favorite: ImageView = view.findViewById(R.id.search_favorite)

    }

}