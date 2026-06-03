package cat.itic.examensegonaconvocatoria.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.itic.examensegonaconvocatoria.R
import cat.itic.examensegonaconvocatoria.models.Llibre

class LlibresAdapter() : RecyclerView.Adapter<LlibreViewHolder>() {

    private var llibres = listOf<Llibre>()

    fun setLlibres(nousLlibres: List<Llibre>) {
        llibres = nousLlibres
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LlibreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_llibre, parent, false)
        return LlibreViewHolder(view)
    }

    override fun onBindViewHolder(holder: LlibreViewHolder, position: Int) {
        holder.renderitza(llibres[position])
    }

    override fun getItemCount(): Int {
        return llibres.size
    }

}