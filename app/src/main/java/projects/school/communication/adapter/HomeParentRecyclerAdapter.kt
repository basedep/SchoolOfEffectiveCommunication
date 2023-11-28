package projects.school.communication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import projects.school.communication.R
import projects.school.communication.model.HomeParentItem

class HomeParentRecyclerAdapter(private val parentList: List<HomeParentItem>, childRecyclerAdapter: CourseRecyclerAdapter) : RecyclerView.Adapter<HomeParentRecyclerAdapter.ParentViewHolder>(){

    //RecyclerViewPool нужен для того чтобы вложенные друг в друга ресайклеры использовали один общий пул для ViewHolder'ов
    //получаем пул для внешнего списка
    private val viewPool = RecyclerView.RecycledViewPool()

    private val childAdapter = childRecyclerAdapter


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {

        //inflate parent's layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_list_item, parent, false)

        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {

        val parentItem = parentList[position] //получаем элемент с которым соеденяем данные

        val layoutManager = LinearLayoutManager(holder.nestedRecycler.context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.initialPrefetchItemCount =
            childAdapter.differ.currentList.size //указываем внутреннему recycler сколько View нужно подготовить перед показом на экран


        holder.apply {
            header.text = parentItem.title //задаем холдеру текст title'a элемента
            nestedRecycler.layoutManager = layoutManager


            nestedRecycler.adapter = childAdapter //адаптер для дочерней recycler
            nestedRecycler.setRecycledViewPool(viewPool)  //задаем ViewPool
        }
    }

    override fun getItemCount(): Int {
        return parentList.size
    }


    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val header: TextView = itemView.findViewById(R.id.home_list_header)
        val nestedRecycler: RecyclerView = itemView.findViewById(R.id.nested_recycler)

    }


}