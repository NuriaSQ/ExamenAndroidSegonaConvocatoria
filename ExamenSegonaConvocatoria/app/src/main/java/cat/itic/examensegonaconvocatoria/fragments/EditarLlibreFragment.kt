package cat.itic.examensegonaconvocatoria.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import cat.itic.examensegonaconvocatoria.LlistaLlibres
import cat.itic.examensegonaconvocatoria.R
import cat.itic.examensegonaconvocatoria.models.Estat
import cat.itic.examensegonaconvocatoria.models.Genere
import cat.itic.examensegonaconvocatoria.models.Llibre
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class EditarLlibreFragment : Fragment() {

    private lateinit var tilTitol: TextInputLayout
    private lateinit var etTitol: TextInputEditText
    private lateinit var etAutor: TextInputEditText
    private lateinit var etAny: TextInputEditText
    private lateinit var spinnerGenere: Spinner
    private lateinit var spinnerEstat: Spinner
    private lateinit var btnTancar: MaterialButton

    private var llibreId: Int = -1
    private var llibreActual: Llibre? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            llibreId = it.getInt("llibre_id", -1)
            if (llibreId != -1) {
                llibreActual = LlistaLlibres.LlibresRepository.llibres.find { llibre -> llibre.id == llibreId }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_editar_llibre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupSpinners()
        setupButtons()

        // Carregar dades del llibre
        llibreActual?.let { carregarDadesLlibre(it) }
    }

    private fun initViews(view: View) {
        tilTitol = view.findViewById(R.id.tilTitol)
        etTitol = view.findViewById(R.id.etTitol)
        etAutor = view.findViewById(R.id.etAutor)
        etAny = view.findViewById(R.id.etAny)
        spinnerGenere = view.findViewById(R.id.spinnerGenere)
        spinnerEstat = view.findViewById(R.id.spinnerEstat)
        btnTancar = view.findViewById(R.id.btnTancar)
    }

    private fun setupSpinners() {
        // Spinner de gèneres
        val generes = listOf("Novel·la", "Assaig", "Còmic")
        val adapterGenere = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            generes
        )
        adapterGenere.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGenere.adapter = adapterGenere

        // Spinner d'estats
        val estats = listOf("Per llegir", "Llegint", "Llegit")
        val adapterEstat = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            estats
        )
        adapterEstat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEstat.adapter = adapterEstat
    }

    private fun setupButtons() {
        btnTancar.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun carregarDadesLlibre(llibre: Llibre) {
        etTitol.setText(llibre.titol)
        etAutor.setText(llibre.autor)
        etAny.setText(llibre.any.toString())

        // Seleccionar gènere
        val posicioGenere = when (llibre.genere) {
            is Genere.Novella -> 0
            is Genere.Assaig -> 1
            is Genere.Comic -> 2
        }
        spinnerGenere.setSelection(posicioGenere)

        // Seleccionar estat
        val posicioEstat = when (llibre.estat) {
            is Estat.PerLlegir -> 0
            is Estat.Llegint -> 1
            is Estat.Llegit -> 2
        }
        spinnerEstat.setSelection(posicioEstat)
    }
}