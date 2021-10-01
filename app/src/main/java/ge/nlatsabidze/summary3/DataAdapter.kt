package ge.nlatsabidze.summary3

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.info.view.*

class DataAdapter(private val information: MutableList<Data>): RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.info,parent,false))
    }

    override fun onBindViewHolder(holder: DataAdapter.DataViewHolder, position: Int) {
        val curInfo = information[position]
        holder.itemView.tvInfo.text = curInfo.info
        holder.itemView.cbDone.isChecked = curInfo.isChecked
        holder.itemView.cbDone.setOnCheckedChangeListener { _, isChecked ->
            curInfo.isChecked = !curInfo.isChecked
        }
    }

    fun addUser(data: Data) {
        information.add(data)
        notifyItemInserted(information.size - 1)
    }

    fun deleteUser() {
        information.removeAll { info ->
            info.isChecked
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = information.size

}