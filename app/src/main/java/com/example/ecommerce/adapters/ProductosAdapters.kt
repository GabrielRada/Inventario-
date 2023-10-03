package com.example.ecommerce4.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.R
import com.example.ecommerce.details_product

import com.example.tiendavirtual.models.Items
import com.example.tiendavirtual.models.Producto


class ProductosAdapters(private val productos: ArrayList<Producto>, private val context: Context) :
    RecyclerView.Adapter<ProductosAdapters.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nombreTextView: TextView = itemView.findViewById(R.id.tvNameShoeDisplayItem)
        val precioTextView: TextView = itemView.findViewById(R.id.tvPriceShoeDisplayItem)
        val imagenImageView: ImageView = itemView.findViewById(R.id.ivShoeDisplayItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val productoView = inflater.inflate(R.layout.product_display_main, parent, false)
        return ViewHolder(productoView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos[position]
        holder.nombreTextView.text = producto.nombre
        holder.precioTextView.text = "Precio: ${producto.precio}"

        // Carga la imagen utilizando Glide o la biblioteca de tu elección
        val nombreCategoriaFormateado = producto.imagen.toLowerCase().replace(" ", "_")
        val imagenResource = context.resources.getIdentifier("$nombreCategoriaFormateado", "mipmap", context.packageName)
        if (imagenResource != 0) {

            holder.imagenImageView.setImageResource(imagenResource)

            holder.imagenImageView.setOnClickListener {
                val actividadProducto = Intent(holder.itemView.context, details_product::class.java)
                actividadProducto.putExtra("producto_id", producto.id)
                actividadProducto.putExtra("producto_nombre", producto.nombre)
                actividadProducto.putExtra("producto_precio", producto.precio)
                actividadProducto.putExtra("producto_descripcion", producto.descripcion)
                actividadProducto.putExtra("producto_imagen", imagenResource)
                holder.itemView.context.startActivity(actividadProducto)
            }
        } else {
            // Si no se encuentra la imagen, puedes mostrar una imagen predeterminada o realizar alguna otra acción
            holder.imagenImageView.setImageResource(R.mipmap.categoria1)
        }

    }

    override fun getItemCount(): Int {
        return productos.size
    }
}
