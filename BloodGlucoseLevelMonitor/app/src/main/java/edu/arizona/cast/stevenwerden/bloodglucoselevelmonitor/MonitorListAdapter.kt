package edu.arizona.cast.stevenwerden.bloodglucoselevelmonitor

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import edu.arizona.cast.stevenwerden.bloodglucoselevelmonitor.databinding.ListItemMonitorBinding

class GlucoseHolder(
    private val binding: ListItemMonitorBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(glucose: Glucose) {
        binding.averageLevel.text = ((glucose.fasting+glucose.breakfast+glucose.lunch+glucose.dinner)/4).toString()
        binding.currentDate.text = glucose.date.toString()
        if(glucose.fasting < 70 || glucose.fasting > 99) {
            binding.normalBounds.setChecked(true)
        }
        else if(checkLevels(glucose.breakfast) || checkLevels(glucose.lunch) || checkLevels(glucose.dinner)) {
            binding.normalBounds.setChecked(true)
        }
        else {
            binding.normalBounds.setChecked(false)
        }

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${glucose.fasting}, ${glucose.breakfast}, ${glucose.lunch}, ${glucose.dinner}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun checkLevels(item: Int): Boolean {
        if(item < 70 || item > 140) {
            return true
        }
        return false
    }

}


class MonitorListAdapter(
    private val glucoses: List<Glucose>
) : RecyclerView.Adapter<GlucoseHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GlucoseHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemMonitorBinding.inflate(inflater, parent, false)
        return GlucoseHolder(binding)
    }

    override fun onBindViewHolder(holder: GlucoseHolder, position: Int) {
        val glucose = glucoses[position]
        holder.bind(glucose)
    }

    override fun getItemCount() = glucoses.size
}
