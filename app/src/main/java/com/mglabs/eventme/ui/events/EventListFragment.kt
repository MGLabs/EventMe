package com.mglabs.eventme.ui.events

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mglabs.eventme.R
import com.mglabs.eventme.data.model.Event
import com.mglabs.eventme.databinding.FragmentEventlistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventListFragment : Fragment(), EventAdapter.ListItemListener {

    private var _binding: FragmentEventlistBinding? = null
    private lateinit var adapter: EventAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val eventList = listOf(
        Event(
            "1",
            "The Event of the Year"
        ),
        Event(
            eventId = "2",
            title = "event 2"
        ),
        Event(
            eventId = "3",
            title = "event 3"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /* val homeViewModel =
             ViewModelProvider(this).get(EventListViewModel::class.java)*/

        _binding = FragmentEventlistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*  val textView: TextView = binding.textHome
          homeViewModel.text.observe(viewLifecycleOwner) {
              textView.text = it
          }*/

        Log.d(" toolbar from fragment 5too", "onCreateView: " + activity?.actionBar)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Adapter & RecycleView
        adapter = EventAdapter(eventList, this)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Implemented function to catch the click event on the row
    override fun editEvent(eventId: String) {
        Navigation.findNavController(binding.root)
            .navigate(EventListFragmentDirections.actionNavigationHomeToAddEditEventFragment(eventId))
    }
}
