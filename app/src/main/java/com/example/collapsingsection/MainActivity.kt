package com.example.collapsingsection

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.collapsingsection.adapters.RequirementSectionAdapter
import com.example.collapsingsection.models.Requirement
import com.example.collapsingsection.models.RequirementItem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val requirementsItemList = listOf(
        RequirementItem(
            "CURRENT POSITION",
            "Registered nurse",
            "Requirements not satisfied",
            listOf(
                Requirement("Advanced cardiovascular", "Board"),
                Requirement("Advanced cardiovascular", "Board")
            )
        ),
        RequirementItem(
            "APPLIED",
            "Physical therapist",
            "Requirements satisfied",
            listOf(
                Requirement("Advanced cardiovascular", "Board"),
                Requirement("Advanced cardiovascular", "Board")
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myItemListRecyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = RequirementSectionAdapter(requirementsItemList)
        }
    }
}
