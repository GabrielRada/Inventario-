package com.example.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce4.adapters.ProductosAdapters
import com.example.tiendavirtual.models.Producto


import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.firestore.FirebaseFirestore

class Categorias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)
        val categoria = findViewById<MaterialToolbar>(R.id.detailActualToolbar)
        val recyclerViewProductosCategoria = findViewById<RecyclerView>(R.id.recyclerViewProductosCategoria)
        val categoriaName = intent.getStringExtra("categoria_nombre")
        categoria.title = categoriaName.toString()

        val layoutManagerProduct = GridLayoutManager(this,2)
        recyclerViewProductosCategoria.layoutManager = layoutManagerProduct
        val productosRef = FirebaseFirestore.getInstance().collection("productos")
            .get()
        val productos = ArrayList<Producto>()
        productosRef
            .addOnSuccessListener { result ->
                Log.d("Productos",result.size().toString())
                for (document in result) {
                    val idProducto = document.getString("id")
                    val nombre = document.getString("nombre")
                    val imagen = document.getString("imagen")
                    val precio = document.getString("precio")
                    val descripcion = document.getString("descripcion")
                    val categoriaId = document.getString("categoria")
                    val producto = Producto(idProducto.toString(),nombre.toString(),precio.toString(),imagen.toString(),categoriaId.toString(),descripcion.toString())
                    if(categoriaId==categoriaName){
                        productos.add(producto)
                    }
                }
                val adapterProduct = ProductosAdapters(productos,this) // Debes crear un adaptador personalizado
                recyclerViewProductosCategoria.adapter = adapterProduct
                // Aquí puedes configurar un adaptador y RecyclerView para mostrar los productos
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error al obtener productos: $exception")
            }
        categoria.setOnClickListener {
            finish()
        }
    }
}