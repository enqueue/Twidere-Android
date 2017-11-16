package org.mariotaku.twidere.fragment

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import org.mariotaku.ktextension.Bundle
import org.mariotaku.ktextension.set
import org.mariotaku.twidere.R
import org.mariotaku.twidere.constant.IntentConstants.EXTRA_PERMISSIONS
import org.mariotaku.twidere.constant.IntentConstants.EXTRA_REQUEST_CODE
import org.mariotaku.twidere.extension.applyTheme
import org.mariotaku.twidere.extension.message
import org.mariotaku.twidere.extension.onShow

class PermissionRequestDialogFragment : BaseDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = this.activity!!
        val builder = AlertDialog.Builder(activity)
        val permissions = arguments!!.getStringArray(EXTRA_PERMISSIONS)
        val requestCode = arguments!!.getInt(EXTRA_REQUEST_CODE)
        builder.setMessage(arguments!!.message)
        builder.setPositiveButton(android.R.string.ok) { _, _ ->
            ActivityCompat.requestPermissions(activity, permissions, requestCode)
        }
        builder.setNegativeButton(R.string.action_later) { _, _ ->
            val callback = parentFragment as? PermissionRequestCancelCallback ?: activity as?
                    PermissionRequestCancelCallback ?: return@setNegativeButton
            callback.onRequestPermissionCancelled(requestCode)
        }
        val dialog = builder.create()
        dialog.onShow { it.applyTheme() }
        return dialog
    }

    interface PermissionRequestCancelCallback {
        fun onRequestPermissionCancelled(requestCode: Int)
    }

    companion object {

        fun show(fragmentManager: FragmentManager, message: String, permissions: Array<String>,
                requestCode: Int): PermissionRequestDialogFragment {
            val df = PermissionRequestDialogFragment()
            df.arguments = Bundle {
                this.message = message
                this[EXTRA_PERMISSIONS] = permissions
                this[EXTRA_REQUEST_CODE] = requestCode
            }
            df.show(fragmentManager, "request_permission_message")
            return df
        }

    }
}