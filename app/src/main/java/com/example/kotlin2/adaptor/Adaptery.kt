package com.example.kotlin2.adaptor
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin2.Haber
import com.example.kotlin2.R
import com.example.kotlin2.util.downloadFromUrl
import com.example.kotlin2.util.pladeHolderProgressBar
import com.example.kotlin2.view.DetailsFragment
import com.example.kotlin2.view.ListFragmentDirections
import kotlinx.android.synthetic.main.row.view.*

class Adaptery(val habers:ArrayList<Haber>): RecyclerView.Adapter<Adaptery.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptery.Holder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Adaptery.Holder, position: Int) {
        holder.view.rowName.text=habers.get(position).name

        holder.view.rowImage.downloadFromUrl(habers.get(position).image, pladeHolderProgressBar(holder.view.context))


        holder.itemView.setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToDetailsFragment(habers[position].id)
            Navigation.findNavController(it).navigate(action)
        }



    }

    override fun getItemCount(): Int {
        return habers.size
    }

    class Holder(var view:View):RecyclerView.ViewHolder(view){

    }
    fun updateHaberList(newHaberList:List<Haber>) {
        habers.clear()
        habers.addAll(newHaberList)
        notifyDataSetChanged()
    }
}