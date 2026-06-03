package cat.itic.examensegonaconvocatoria.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.itic.examensegonaconvocatoria.R
import cat.itic.examensegonaconvocatoria.models.Estat
import cat.itic.examensegonaconvocatoria.models.Genere
import cat.itic.examensegonaconvocatoria.models.Llibre

class LlibreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titol = itemView.findViewById<TextView>(R.id.tvTitol)
    val autor = itemView.findViewById<TextView>(R.id.tvAutor)
    val genere = itemView.findViewById<TextView>(R.id.tvGenere)
    val estat = itemView.findViewById<TextView>(R.id.tvEstat)
    val any = itemView.findViewById<TextView>(R.id.tvAny)

    fun renderitza( modelLlibre : Llibre){

        titol.text = modelLlibre.titol
        autor.text = modelLlibre.autor
        genere.text = when (modelLlibre.genere) {
            Genere.Assaig -> "Assaig"
            Genere.Comic -> "Comic"
            Genere.Novella -> "Novel·la"
        }

        estat.text = when (modelLlibre.estat) {
            Estat.PerLlegir -> "Per Llegir"
            Estat.Llegint -> "Llegint"
            Estat.Llegit -> "Llegit"
        }

      any.text = modelLlibre.any.toString()


    }

}