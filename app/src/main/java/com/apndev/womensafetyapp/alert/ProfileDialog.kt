package  com.apndev.womensafetyapp.alert

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.apndev.womensafetyapp.Profile
import com.apndev.womensafetyapp.databinding.AlertOkBinding
import com.apndev.womensafetyapp.databinding.DialogProfileBinding


class ProfileDialog : DialogFragment() {


    var mBaseDialog: MBaseDialog?=null
 
    var from: String? = "Profile"

    companion object {

        var closed = true
       
        fun newInstance(content: String): ProfileDialog {
            val f = ProfileDialog()

            val args = Bundle()

            f.arguments = args

            return f
        }
        fun newInstance(): ProfileDialog {
            val f = ProfileDialog()
            val args = Bundle()
            f.arguments = args

            return f
        }
        fun newInstance(mBaseDialog: MBaseDialog): ProfileDialog {
            val f = ProfileDialog()

            val args = Bundle()
            args.putParcelable("dialog_bundle",mBaseDialog)

            f.arguments = args

            return f
        }
        fun newInstance(mBaseDialog: MBaseDialog, from:String): ProfileDialog {
            val f = ProfileDialog()

            val args = Bundle()
            args.putParcelable("dialog_bundle",mBaseDialog)
            args.putString("from", from)

            f.arguments = args

            return f
        }
        fun showDialog(context: Activity) :ProfileDialog{

            val newFragment = ProfileDialog.newInstance()
            try {
                if (!context.isFinishing && !context.isDestroyed) {
                    val fragmentManager = (context as FragmentActivity).supportFragmentManager

                    val transaction = fragmentManager.beginTransaction()
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    transaction.add(android.R.id.content, newFragment)
                        .addToBackStack(null).commit()
                }
            } catch (e: Exception) {
                e.message
            }
            return newFragment
        }
        fun showDialog(context: Activity, mBaseDialog: MBaseDialog) :ProfileDialog{
            val newFragment = ProfileDialog.newInstance(mBaseDialog)
            try {

                if (!context.isFinishing && !context.isDestroyed) {
                    val fragmentManager = (context as FragmentActivity).supportFragmentManager

                    val transaction = fragmentManager.beginTransaction()
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    transaction.add(android.R.id.content, newFragment)
                        .addToBackStack(null).commit()


                }
            } catch (e: Exception) {
                e.message
            }

            return newFragment

        }
        fun showDialog(context: Activity, mBaseDialog: MBaseDialog, from:String) :ProfileDialog{
            val newFragment = ProfileDialog.newInstance(mBaseDialog,from)

            try {
                if (!context.isFinishing && !context.isDestroyed) {
                    val fragmentManager = (context as FragmentActivity).supportFragmentManager

                    val transaction = fragmentManager.beginTransaction()
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    transaction.add(android.R.id.content, newFragment)
                        .addToBackStack(null).commit()
                }
            } catch (e: Exception) {
                e.message
            }
            return newFragment
        }




    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBaseDialog = getArguments()?.getParcelable("dialog_bundle")


        from= mBaseDialog?.from

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

     //   _binding = AlertOkBinding.inflate(inflater, container, false)
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    private var _binding: DialogProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = DialogProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
      //  return inflater.inflate(R.layout.alert_ok, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closed = false


        mBaseDialog?.let {

       /*     if(mBaseDialog!!.close_icon!!)
                binding.  closeRlay.visibility= View.VISIBLE
            else
                binding.  closeRlay.visibility= View.GONE*/
            it. ok_text?.let {
                binding.  okTxv.setText(it)


            }




        }?:let {


        }
        setErrorText("")
        val listener = activity as OnDismissListner?
        binding. topRlay.setOnClickListener(View.OnClickListener { })
        binding. topRlay.setOnTouchListener(View.OnTouchListener { v, event -> false })
    /*    binding.closeRlay.setOnClickListener(View .OnClickListener {
            listener?.onDismiss(false)
            onBackFinish()

        })*/
        binding. createRlay.setOnClickListener(View.OnClickListener {


            listener?.onDismiss(Profile(name=binding.nameEdt.text.toString(),
                    phone= binding.phoneEdt.text.toString(),
                    email =binding.emailEdt.text.toString(),
                    ))

            onBackFinish()

        })

     //   showKeyboard(otp_pinv)
    }

    fun setOTPText(text: String) {
    }

    fun setErrorText(text: String) {
        Log.d("_sms", "setErrorText text:" + text)
      //  Log.d("_sms", "setErrorText error_txv :" + error_txv?.text)
      //  error_txv?.setText(text)
    }

    fun popupDismiss() {
        val listener = activity as OnDismissListner?
        listener?.onDismiss(false)
        onBackFinish()
    }

    fun redirectLogin(activity: Activity?) {
        activity?.let {
         /*   ToastHandler.newInstance(activity).mustShowToastSessionExpired()
            HomeSignTabActivity.tab_position = 4
            CCRBXActivity.class_name = activity?.javaClass
            val intent = Intent(activity, SignktActivity::class.java)
            startActivity(intent)*/
        }
    }


    fun onBackFinish() {

        closed = true
        dismiss()
    }




}
