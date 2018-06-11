package com.stellar1198.a6thseminar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main6.*

class MainActivity6 : AppCompatActivity(), View.OnClickListener {

    lateinit var pokemonRealm : Realm
    lateinit var pokemonCollections: ArrayList<PokmonVO>
    lateinit var pokemonVO: PokmonVO
    lateinit var collectionAdapter: CollectionAdapter
    lateinit var id : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
        init()

        main_insert_button.setOnClickListener {
            insertPokemonList()
            pokemonCollections = getPokemonList().toMutableList() as ArrayList<PokmonVO>
            collectionAdapter = CollectionAdapter(pokemonCollections)
            collectionAdapter.setOnItemClickListner(this)
            main_collection_list.adapter = collectionAdapter
        }

        main_logout_button.setOnClickListener {
            SharedPreferenceController.clearSPC(this)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    fun init() {
        Realm.init(this)
        pokemonRealm = Realm.getDefaultInstance()
        id = intent.getStringExtra("id")
        main_collection_list.layoutManager = LinearLayoutManager(this)
        pokemonCollections = ArrayList()
        pokemonCollections = getPokemonList().toMutableList() as ArrayList<PokmonVO>
        collectionAdapter = CollectionAdapter(pokemonCollections)
        collectionAdapter.setOnItemClickListner(this)
        main_collection_list.adapter = collectionAdapter
    }

    fun getPokemonList() : RealmResults<PokmonVO> {
        return pokemonRealm.where(PokmonVO::class.java).equalTo("id", id).findAll()
    }

    fun insertPokemonList(){
        pokemonVO = PokmonVO()
        pokemonVO.num = main_num_edit.text.toString().toInt()
        pokemonVO.name = main_name_edit.text.toString()
        pokemonVO.type = main_type_edit.text.toString()
        pokemonVO.user = id
        pokemonRealm.beginTransaction()
        pokemonRealm.copyToRealm(pokemonVO)
        pokemonRealm.commitTransaction()

        Toast.makeText(this, "등록성공", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?){
        val idx : Int = main_collection_list.getChildAdapterPosition(v)
        val name : String = pokemonCollections[idx].name
        deletePokemonList(name)
    }

    fun deletePokemonList(name : String){
        val result = pokemonRealm.where(PokmonVO::class.java).equalTo("name", name).findAll()
        if (result.isEmpty()){
            return
        }
        pokemonRealm.beginTransaction()
        result.deleteAllFromRealm()
        pokemonRealm.commitTransaction()
        Toast.makeText(this, "삭제 성공", Toast.LENGTH_SHORT).show()

        pokemonCollections = getPokemonList().toMutableList() as ArrayList<PokmonVO>
        collectionAdapter = CollectionAdapter(pokemonCollections)
        collectionAdapter.setOnItemClickListner(this)
        main_collection_list.adapter = collectionAdapter
    }
}
