package wegotrip.task.presentation.main

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import wegotrip.task.R
import wegotrip.task.databinding.BottomSheetFirstPageBinding


@AndroidEntryPoint
class BottomSheetFragment: BottomSheetDialogFragment() {

    private var dialog : BottomSheetDialog? = null
    private var _binding: BottomSheetFirstPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog!!.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        return dialog!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetFirstPageBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() {
        val navHost = NavHostFragment()
        childFragmentManager.beginTransaction().replace(R.id.fragment, navHost).commitNow()
        navHost.navController.setGraph(R.navigation.nav_bottom_sheet)

        with(binding) {
            buttonColorful.setOnClickListener {
                navHost.navController.navigate(R.id.action_fragment_one_to_fragment_two)
                buttonColorful.setText(R.string.save_request)
                buttonBorderless.setText(R.string.skip)
            }
            buttonBorderless.setOnClickListener {
                dismiss()
            }
        }
    }

    companion object {
        const val TAG = "BottomSheetFragment"
    }
}