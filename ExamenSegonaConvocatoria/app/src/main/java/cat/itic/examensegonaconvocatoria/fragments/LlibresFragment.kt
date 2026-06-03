package cat.itic.examensegonaconvocatoria.fragments
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import cat.itic.examensegonaconvocatoria.LlistaLlibres
import cat.itic.examensegonaconvocatoria.R
import cat.itic.examensegonaconvocatoria.adapter.LlibresAdapter
import cat.itic.examensegonaconvocatoria.models.Genere
import com.google.android.material.chip.Chip
import androidx.core.graphics.toColorInt

class LlibresFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var chipGroup: ChipGroup
    private lateinit var toolbar: Toolbar
    private lateinit var adapter: LlibresAdapter
    private var genereSeleccionat: Genere? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_llibres, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.toolbar)
        recyclerView = view.findViewById(R.id.recyclerview)
        chipGroup = view.findViewById(R.id.chipgroup)

        setupRecyclerView()
        setupChips()
        setupToolbarBehavior()
        //canviarColorsText(view)
    }

    private fun setupRecyclerView() {
        val manager = LinearLayoutManager(requireContext())

        adapter = LlibresAdapter()
        adapter.setLlibres(LlistaLlibres.llibres)

        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }

    private fun setupChips() {
        val chipTots: Chip = view?.findViewById(R.id.chipTots) ?: return
        val chipNovella: Chip = view?.findViewById(R.id.chipNovella) ?: return
        val chipAssaig: Chip = view?.findViewById(R.id.chipAssaig) ?: return
        val chipComic: Chip = view?.findViewById(R.id.chipComic) ?: return

        chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            genereSeleccionat = when {
                checkedIds.contains(R.id.chipNovella) -> Genere.Novella
                checkedIds.contains(R.id.chipAssaig) -> Genere.Assaig
                checkedIds.contains(R.id.chipComic) -> Genere.Comic
                else -> null
            }
            actualitzarLlibres()
        }
    }

    private fun setupToolbarBehavior() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    // Scroll cap avall - amagar toolbar
                    toolbar.animate()
                        .translationY(-toolbar.height.toFloat())
                        .setDuration(200)
                        .start()
                } else if (dy < 0) {
                    // Scroll cap amunt - mostrar toolbar
                    toolbar.animate()
                        .translationY(0f)
                        .setDuration(200)
                        .start()
                }
            }
        })
    }

    private fun actualitzarLlibres() {
        if (this::adapter.isInitialized) {


            val llibresFiltrats = if (genereSeleccionat != null) {
                LlistaLlibres.LlibresRepository.llibres.filter {
                    it.genere::class == genereSeleccionat!!::class
                }
            } else {
                LlistaLlibres.LlibresRepository.llibres
            }
            adapter.setLlibres(llibresFiltrats)
        }
    }

    private fun canviarColorsText(view : View){
        val edText = view.findViewById<TextView>(R.id.tvEstat)

        if(edText.text == "Per Llegir") {
            edText.setTextColor("#FF9800".toColorInt())
        }
        else if (edText.text == "Llegint"){
            edText.setTextColor("#2196F3".toColorInt())
        }
        else edText.setTextColor("#4CAF50".toColorInt())
    }
}