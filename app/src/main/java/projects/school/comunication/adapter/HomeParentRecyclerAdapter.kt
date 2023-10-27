package projects.school.comunication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import projects.school.comunication.R
import projects.school.comunication.model.HomeParentItem

class HomeParentRecyclerAdapter(list: List<HomeParentItem>) : RecyclerView.Adapter<HomeParentRecyclerAdapter.ParentViewHolder>(){

    //RecyclerViewPool нужен для того чтобы вложенные друг в друга ресайклеры использовали один общий пул для ViewHolder'ов
    //получаем пул для внешнего списка
    private val viewPool = RecyclerView.RecycledViewPool()
    private val itemList: List<HomeParentItem> = list //получаем список


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {

        //inflate parent's layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_list_item, parent, false)

        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {

        val parentItem = itemList[position] //получаем элемент с которым соеденяем данные

        val layoutManager = LinearLayoutManager(holder.nestedRecycler.context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.initialPrefetchItemCount =
            parentItem.list.size //указываем внутреннему recycler сколько View нужно подготовить перед показом на экран

        //создаем instance дочернего элемента чтобы указать ему layoutManager, adapter и ViewPool
        val childRecyclerAdapter = HomeChildRecyclerAdapter(parentItem.list)

        holder.apply {
            header.text = parentItem.title //задаем холдеру текст title'a элемента
            nestedRecycler.layoutManager = layoutManager
            nestedRecycler.adapter = childRecyclerAdapter
            nestedRecycler.setRecycledViewPool(viewPool)  //задаем ViewPool
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val header: TextView = itemView.findViewById(R.id.home_list_header)
        val nestedRecycler: RecyclerView = itemView.findViewById(R.id.nested_recycler)

    }


}