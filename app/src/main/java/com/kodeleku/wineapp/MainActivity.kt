package com.kodeleku.wineapp

import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kodeleku.wineapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var  adapter: WineListAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var service: WineService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setupAdapter()
        setupRecyclerView()
        setupRetrofit()
        setupSwipeRefresh()
    }

    // Declaramos la función setupAdapter
    private fun setupAdapter() {
        // Vinculamos nuestro Adapter a la variable
        adapter = WineListAdapter()
    }

    // Declaramos la función setupRecyclerView
    private fun setupRecyclerView() {
        // Vinculamos a esta nuestro RecyclerView de "activity_main.xml"
        binding.recyclerView.apply {
            // De igual forma que en el XML debemos indicar el tipo de layout y en nuestro caso al ser GridLayout (columnas, y orientación)
            layoutManager = StaggeredGridLayoutManager (3,RecyclerView.VERTICAL) // De esta forma sobrescribimos para tener una configuración en tiempo real en lugar de estar condicionado únicamente a nuestro diseño
            // Debemos tener en cuenta que queremos que el adapter de RecyclerView sea el adapter que hemos declarado en la clase MainActivity
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupSwipeRefresh() {
        binding.srlWines.setOnRefreshListener {
            // Inicia la recarga llamando a la función de obtención de datos
            getWines()
        }
    }

    private fun setupRetrofit(){
        // Instanciación y seleccionar construcción:
        val retrofit =Retrofit.Builder()
            // URL BASE
            .baseUrl(Constants.BASE_URL)
            // Transforma objeto JSON (GSON) en objetos Kotlin
            .addConverterFactory(GsonConverterFactory.create())
            // Construir
            .build()
        // Definir la instanciación del servicio basandonos en la configuración que acabamos de hacer
        service = retrofit.create(WineService::class.java)
    }

    /**
     * Método getWines anotado con @RequiresExtension para limitar su uso a Android 12 (API 31, versión 7) o superior.
     * Actualmente, no está diseñado para ejecutarse en versiones inferiores.
     *
     * NOTA: Para soportar versiones de Android anteriores, se debe implementar un manejo condicional de versiones
     * en los lugares donde se llama este método.
     */
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getWines() {
        //val wines = getLocalWines()
        lifecycleScope.launch {
            // Mostrar la animación de SwipeRefresh al iniciar la solicitud
            binding.srlWines.isRefreshing = true
            try {
                // Consumir el servicio en el contexto de IO
                val wines = withContext(Dispatchers.IO) {
                    service.getRedWines()
                }
                // Usamos el método "submitList(Lista)", método propio de la clase "ListAdapter"
                adapter.submitList(wines)

            } catch (e: HttpException) {
                // Manejar errores relacionados con el servidor
                Log.e("MainActivity", "HTTP Error: ${e.message}", e)
                Toast.makeText(this@MainActivity, "Error de servidor: ${e.message}", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                // Manejar errores de conexión o red
                Log.e("MainActivity", "Network Error: ${e.message}", e)
                Toast.makeText(this@MainActivity, "Error de red, verifica tu conexión", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                // Manejar cualquier otro tipo de error
                Log.e("MainActivity", "Unexpected Error: ${e.message}", e)
                Toast.makeText(this@MainActivity, "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show()
            } finally {
                // Asegurarse de ocultar la animación de SwipeRefresh sin importar el resultado
                binding.srlWines.isRefreshing = false
            }
        }
    }

    // Objetos añadidos de forma local en la función temporal
    private fun getLocalWines() = listOf(
        Wine(
            "Kodeleku",
            "Chardonnay",
            Rating("4.5", "158 ratings"),
            "Navarra (ES)",
            "https://images.vivino.com/thumbs/ApnIiXjcT5Kc33OHgNb9dA_375x500.jpg",
            1
        ),
        Wine(
            "Rioja Fino",
            "Tempranillo",
            Rating("4.2", "230 ratings"),
            "La Rioja (ES)",
            "https://images.vivino.com/thumbs/GpcSXs2ERS6niDxoAsvESA_pb_x300.png",
            2
        ),
        Wine(
            "Sierra Verde",
            "Sauvignon Blanc",
            Rating("4.3", "145 ratings"),
            "Castilla y León (ES)",
            "https://images.vivino.com/thumbs/PBhGMcRNQ7aVnVNr7VgnWA_pb_x300.png",
            3
        ),
        Wine(
            "Montaña Roja",
            "Grenache",
            Rating("4.6", "87 ratings"),
            "Aragón (ES)",
            "https://images.vivino.com/thumbs/ZzMKzqFqRO-6oI3ys3gGgQ_pb_x300.png",
            4
        ),
        Wine(
            "Viña Blanca",
            "Albariño",
            Rating("4.4", "300 ratings"),
            "Galicia (ES)",
            "https://images.vivino.com/thumbs/easjTPIcS-mCQ99XoYOMgQ_pb_x300.png",
            5
        ),
        Wine(
            "Bodega del Sol",
            "Syrah",
            Rating("4.1", "215 ratings"),
            "Andalucía (ES)",
            "https://images.vivino.com/thumbs/U19RXtSdRMmoAesl2CBygA_pb_x300.png",
            6
        ),
        Wine(
            "Terra Negra",
            "Cabernet Sauvignon",
            Rating("4.5", "412 ratings"),
            "Cataluña (ES)",
            "https://images.vivino.com/thumbs/pU7uFKR-TAKAOQaf3Hpn2A_pb_x300.png",
            7
        ),
        Wine(
            "Finca Dorada",
            "Merlot",
            Rating("4.0", "120 ratings"),
            "Valencia (ES)",
            "https://images.vivino.com/thumbs/HYVZMFigQ5qXxni7s9SpWw_pb_x300.png",
            8
        ),
        Wine(
            "Costa Azul",
            "Malbec",
            Rating("4.3", "178 ratings"),
            "Murcia (ES)",
            "https://images.vivino.com/thumbs/V5JCHLK_SxSiWxhghoQ1yQ_375x500.jpg",
            9
        ),
        Wine(
            "Campo Viejo",
            "Pinot Noir",
            Rating("4.6", "520 ratings"),
            "Navarra (ES)",
            "https://images.vivino.com/thumbs/Yt464jw0QS-ugF7ZQEbE2Q_pb_x300.png",
            10
        )
    )

    /**
     * Método onResume anotado con @RequiresExtension para limitar su uso a Android 12 (API 31, versión 7) o superior.
     * Actualmente, la funcionalidad de `getWines()` no maneja versiones inferiores.
     *
     * NOTA: Para ampliar la compatibilidad, es necesario agregar un bloque condicional en el método
     * que verifique la versión de Android y proporcione una alternativa adecuada para dispositivos con
     * API menor a 31 o extensión inferior a 7.
     */
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            getWines()
        } else {
            // Alternativa para versiones más antiguas
        }
    }


}