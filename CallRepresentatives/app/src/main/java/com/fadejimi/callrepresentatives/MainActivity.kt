package com.fadejimi.callrepresentatives

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadejimi.callrepresentatives.model.Representative

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {


    val representatives: ArrayList<Representative> = ArrayList()
    lateinit var adapter: RepresentativesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.toolbar_title)

        addRepresentatives()
        adapter = RepresentativesAdapter(this)

        rv.layoutManager = LinearLayoutManager(this)

        rv.adapter = adapter
        adapter.items = representatives
    }

    private fun addRepresentatives() {
        representatives.add(Representative("aba", "Oyo", "Southwest",
            "ade@gm.com", "938304044"))
        representatives.add(Representative("sango", "Oyo", "Southeast",
            "", "938304044"))
        representatives.add(Representative("bisi", "Edo", "Southwest",
            "adefof@gm.com", ""))
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        // Associate searchable configuration with searchview
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE

        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        adapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        adapter.filter.filter(query)
        return false
    }

}
