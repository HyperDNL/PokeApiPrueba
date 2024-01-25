package com.example.pokeprueba.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeprueba.pokemon.PokemonAdapter
import com.example.pokeprueba.pokemon.PokemonListViewModel
import com.example.pokeprueba.databinding.ActivityPokemonListBinding
import com.example.pokeprueba.utils.NavigationUtils
import com.example.pokeprueba.auth.AuthController
import com.example.pokeprueba.auth.LoginActivity

class PokemonListActivity : AppCompatActivity()  {
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var binding: ActivityPokemonListBinding
    private lateinit var pokeListAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokemonListViewModel::class.java]

        initUI()
    }

    private fun initUI() {
        binding.pokelistRecyclerView.layoutManager = LinearLayoutManager(this)


        pokeListAdapter = PokemonAdapter{
            val intent = Intent(this, PokeInfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }


        binding.pokelistRecyclerView.adapter = pokeListAdapter

        viewModel.getPokemonList()
        viewModel.pokemonList.observe(this, Observer { list ->
            pokeListAdapter.setData(list)
        })

        binding.logout.setOnClickListener {
            // Cerrar sesión utilizando el controlador
            AuthController.logoutUser()

            // Redirigir a la pantalla de inicio de sesión
            NavigationUtils.redirectToDestination(this@PokemonListActivity, LoginActivity::class.java)
        }
    }
}