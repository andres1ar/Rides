package ca.andresalderete.rides.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.andresalderete.rides.R
import ca.andresalderete.rides.models.VehicleItem

class AdapterClass(private val vehicles: List<VehicleItem>) : RecyclerView.Adapter<AdapterClass.ViewHolder>() {
    private var onClickListener: OnClickListener? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val makeAndModel: TextView = itemView.findViewById(R.id.makeAndModelTextView)
        val vinNumber: TextView = itemView.findViewById(R.id.vinTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = vehicles[position]
        holder.makeAndModel.text = currentItem.makeAndModel
        holder.vinNumber.text = currentItem.vin

        holder.itemView.setOnClickListener {
            onClickListener?.onClick(currentItem)
        }
    }

    interface OnClickListener {
        fun onClick(vehicle: VehicleItem)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
     this.onClickListener = onClickListener
    }
}
