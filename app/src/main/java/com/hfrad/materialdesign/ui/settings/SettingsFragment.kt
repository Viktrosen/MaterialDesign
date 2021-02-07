package com.hfrad.materialdesign.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.hfrad.materialdesign.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {
    private var isEnabled = false
    private var note = arrayListOf(
            "First note",
            "Second note",
            "Third note"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadListeners()
    }

    fun loadListeners(){

        val adapter = RecyclerNoteAdapter(note)
        recyclerView.adapter = adapter

        fabRecyclerItem.setOnClickListener {
            adapter.appendItem(editText.text.toString())
            editText.text.clear()
        }



        isEnabled = !isEnabled
        if (isEnabled){
        change_theme.setOnCheckedChangeListener { button, isCheked ->
            if (isCheked){
                Toast.makeText(context,"True",Toast.LENGTH_SHORT).show()
                activity?.setTheme(R.style.DarkAppTheme)
                change_theme.isChecked = isEnabled

            }else {
                Toast.makeText(context,"false",Toast.LENGTH_SHORT).show()
                activity?.setTheme(R.style.AppTheme)
                change_theme.isChecked = isCheked
                }
            activity?.let { recreate(it) }

        }}else {
            change_theme.setOnCheckedChangeListener{compoundButton, _ ->  }
        }

        change_theme.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"Just click",Toast.LENGTH_SHORT).show()
        })




    }

}
