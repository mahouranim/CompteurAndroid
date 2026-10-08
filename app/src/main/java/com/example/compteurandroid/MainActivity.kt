package com.example.compteurandroid

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Variable entière qui stocke la valeur du compteur
    private var compteur: Int = 0

    private lateinit var textViewCompteur: TextView

    companion object {
        private const val CLE_COMPTEUR = "cle_compteur"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupère la valeur sauvegardée si l'écran a tourné
        if (savedInstanceState != null) {
            compteur = savedInstanceState.getInt(CLE_COMPTEUR, 0)
        }

        // Récupération des composants graphiques par leurs identifiants
        textViewCompteur = findViewById(R.id.textViewCompteur)
        val buttonIncrementer: Button = findViewById(R.id.buttonIncrementer)
        val buttonDecrementer: Button = findViewById(R.id.buttonDecrementer)
        val buttonReinitialiser: Button = findViewById(R.id.buttonReinitialiser)

        // Affiche la valeur initiale (0 ou valeur restaurée)
        afficherCompteur()

        // Clic sur le bouton +
        buttonIncrementer.setOnClickListener {
            compteur++
            afficherCompteur()
        }

        // Clic sur le bouton -
        buttonDecrementer.setOnClickListener {
            compteur--
            afficherCompteur()
        }

        // Clic sur le bouton Réinitialiser
        buttonReinitialiser.setOnClickListener {
            compteur = 0
            afficherCompteur()
            Toast.makeText(
                this,
                getString(R.string.message_reinitialisation),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Met à jour le texte et la couleur du TextView en fonction du compteur
    private fun afficherCompteur() {
        textViewCompteur.text = compteur.toString()
        val couleur = when {
            compteur > 0 -> Color.parseColor("#2E7D32") // vert
            compteur < 0 -> Color.parseColor("#C62828") // rouge
            else -> Color.BLACK
        }
        textViewCompteur.setTextColor(couleur)
    }

    // Sauvegarde le compteur pour qu'il survive à une rotation d'écran
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CLE_COMPTEUR, compteur)
    }
}
