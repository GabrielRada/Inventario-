package com.example.ecommerce4.adapters
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

import com.example.ecommerce.Categorias
import com.example.ecommerce.R

import com.example.tiendavirtual.models.Categoria



class CategoriaAdapter(private val categorias: List<Categoria>,private val context: Context) : RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.textView10)
        val fotoImageView: ImageView = itemView.findViewById(R.id.imageView5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val categoriaView = inflater.inflate(R.layout.item_categoria, parent, false) // Crea un layout XML para un elemento de categoría
        return ViewHolder(categoriaView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoria = categorias[position]
        holder.nombreTextView.text = categoria.nombre
        // Utiliza Glide para cargar la imagen desde la URL y mostrarla en el ImageView
        val nombreCategoriaFormateado = categoria.imagen.toLowerCase().replace(" ", "_")
        val imagenResource = context.resources.getIdentifier("$nombreCategoriaFormateado", "mipmap", context.packageName)
        holder.fotoImageView.setOnClickListener {
            val actividadCategoria = Intent(holder.itemView.context,Categorias::class.java)
            actividadCategoria.putExtra("categoria_nombre", categoria.nombre)
            holder.itemView.context.startActivity(actividadCategoria)
        }
        if (imagenResource != 0) {
            holder.fotoImageView.setImageResource(imagenResource)


        } else {
            // Si no se encuentra la imagen, puedes mostrar una imagen predeterminada o realizar alguna otra acción
            holder.fotoImageView.setImageResource(R.mipmap.categoria1)
        }

    }

    override fun getItemCount(): Int {
        return categorias.size
    }
}