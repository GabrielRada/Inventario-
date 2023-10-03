package com.example.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce4.adapters.ProductosAdapters
import com.example.tiendavirtual.models.Producto


import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.firestore.FirebaseFirestore

class ProductAll : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_all)
        val recyclerViewProductos = findViewById<RecyclerView>(R.id.recyclerViewProductos)
        val volver = findViewById<MaterialToolbar>(R.id.cartActualToolbar)
        val layoutManagerProduct = GridLayoutManager(this,2)
        recyclerViewProductos.layoutManager = layoutManagerProduct
        val productosRef = FirebaseFirestore.getInstance().collection("productos")
            .get()
        val productos = ArrayList<Producto>()
        productosRef
            .addOnSuccessListener { result ->
                for (document in result) {
                    val idProducto = document.getString("id")
                    val nombre = document.getString("nombre")
                    val imagen = document.getString("imagen")
                    val precio = document.getString("precio")
                    val categoriaId = document.getString("categoria")
                    val descripcion = document.getString("descripcion")
                    val producto = Producto(idProducto.toString(),nombre.toString(),precio.toString(),imagen.toString(),categoriaId.toString(),descripcion.toString())
                    productos.add(producto)
                }
                val adapterProduct = ProductosAdapters(productos,this) // Debes crear un adaptador personalizado
                recyclerViewProductos.adapter = adapterProduct
                // Aquí puedes configurar un adaptador y RecyclerView para mostrar los productos
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error al obtener productos: $exception")
            }
        volver.setOnClickListener {
            finish()
        }
    }

}