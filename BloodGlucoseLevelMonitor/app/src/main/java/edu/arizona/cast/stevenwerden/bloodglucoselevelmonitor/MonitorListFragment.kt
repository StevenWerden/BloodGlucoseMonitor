package edu.arizona.cast.stevenwerden.bloodglucoselevelmonitor

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import edu.arizona.cast.stevenwerden.bloodglucoselevelmonitor.databinding.FragmentMonitorListBinding

class MonitorListFragment : Fragment() {

    private var _binding: FragmentMonitorListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Binding is null. Check if view is visible"
        }

    private val monitorListViewModel: MonitorListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total glucose entries: ${monitorListViewModel.glucoses.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonitorListBinding.inflate(inflater, container, false)

        binding.monitorRecyclerView.layoutManager = LinearLayoutManager(context)

        val glucoses = monitorListViewModel.glucoses
        val adapter = MonitorListAdapter(glucoses)
        binding.monitorRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}