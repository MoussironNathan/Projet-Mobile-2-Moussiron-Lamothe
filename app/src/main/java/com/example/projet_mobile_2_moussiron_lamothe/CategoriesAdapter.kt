package com.example.projet_mobile_2_moussiron_lamothe

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CategoriesAdapter(val categories: ArrayList<Categories>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewCategorie: ImageView = view.findViewById<ImageView>(R.id.imageProduit)
        val textViewCategorieTitle: TextView = view.findViewById<TextView>(R.id.produitName)
        val textViewCategorieDesc: TextView = view.findViewById<TextView>(R.id.produitDesc)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cell_categories, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categorie = categories[position]
        Picasso.get().load(categorie.picture_url).into(holder.imageViewCategorie)
        holder.textViewCategorieTitle.text = categorie.name
        holder.textViewCategorieDesc.text = categorie.description
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}