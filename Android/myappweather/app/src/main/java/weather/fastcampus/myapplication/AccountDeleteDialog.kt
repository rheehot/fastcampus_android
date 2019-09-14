package weather.fastcampus.myapplication

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.account_delete_dialog.*

class AccountDeleteDialog  : DialogFragment(){

    interface AccountDeleteDialogInterface{
        fun delete()
        fun cancelDelete()
    }

    private var accountDeleteDialogInterface : AccountDeleteDialogInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.account_delete_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListener()
    }

    fun addAccountDeleteDialogInterface(listener : AccountDeleteDialogInterface){
        accountDeleteDialogInterface = listener
    }

    private fun setupListener(){
        delete_no.setOnClickListener {
            accountDeleteDialogInterface?.cancelDelete()
            dismiss()
        }
        delete_yes.setOnClickListener {
            accountDeleteDialogInterface?.delete()
            dismiss()
        }
    }
}