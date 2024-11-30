package com.kodeleku.wineapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kodeleku.wineapp.databinding.ItemWineBinding

// Clase Default -> De tipo ListAdapter, hereda de Wine y RecyclerView.ViewHolder, y recibe el constructor de "WineDiff" vacío
class WineListAdapter: ListAdapter<Wine, RecyclerView.ViewHolder>(WineDiff()) {

    private lateinit var context: Context


    // Método miembro para inflar el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        // Le proporcionamos el contexto de ViewGroup y lo inflamos con la ruta que deseamos usar (item_wine), y los 2 parametros por defecto para inflar la vista (parent, y attachToRoot:False)
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_wine, parent, false))   // R representa "res"
    }

    // Método para una vez inflada la vista setear cada elemento de la ruta "res.layout.item_wine"
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Creamos la variable wine para los hacer get a los items pasandole la posición
        val wine = getItem(position)
        // Debemos castear holder como ViewHolder ya que el holder que se recibe por parametro es de "RecyclerView.ViewHolder" y no de nuestro "inner class ViewHolder"
        // Usamos la función de extensión "run" para añadir la lógica con el contexto de "WineListAdapter.ViewHolder", nuestra clase interna en definitiva
        (holder as ViewHolder).run {
            // Añadimos: con(binding) -> con nuestra vista que procede de item_wine -> Esto nos otorga acceso a todos los identificadores de esta dentro de ViewHolder
            with(binding) {
                // En este nivel tenemos acceso a los identificadores de la vista
                tvWine.text = wine.wine
                tvWinery.text = wine.winery
                tvLocation.text = wine.location
                // En el caso de rating seleccionamos el promedio (average) y como este es Float y no String debemos castearlo con ".toFloat()"
                rating.rating = wine.rating.average.toFloat()

                // llamamos a GLIDE y le pasamos el contexto que al declararse globalmente es "ambiguo", así que generamos uno
                Glide.with(context)
                    .load(wine.image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(ivWine)
            }
        }
    }

    // Clase interna -> ViewHolder personalizado que recibe una instancia de View y hereda de "RecyclerView.ViewHolder" y también recibe la vista
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        // Creamos el binding para manejar la vista de itemWine en la clase de kotlin
        val binding = ItemWineBinding.bind (view);
    }


    // Clase privada -> Wine Diff de tipo DiffUtil.ItemCallback,hereda de Wine
    private class WineDiff: DiffUtil.ItemCallback<Wine>() {
        // Métodos miembros de DiffUtil que comparan los items de "Wine"
        override fun areItemsTheSame(oldItem: Wine, newItem: Wine): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Wine, newItem: Wine): Boolean {
            return oldItem == newItem
        }
    }
}