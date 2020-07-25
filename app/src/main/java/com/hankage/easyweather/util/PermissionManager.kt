package com.hankage.easyweather.util

import android.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.hankage.easyweather.R
import com.yanzhenjie.permission.AndPermission
import java.lang.ref.SoftReference

/**
 * Author: cheers
 * Time ： 2020-07-25
 * Description ：
 */
object PermissionManager {

    fun requestPermission(softReference : SoftReference<FragmentActivity>, vararg permissions: String){
        AndPermission.with(softReference.get())
            .runtime()
            .permission(permissions)
            .rationale { context, data, executor ->
                val builder = AlertDialog.Builder(softReference.get())
                builder.run {
                    setMessage(R.string.permission_tips)
                    setCancelable(false)
                    setPositiveButton(R.string.sure) { dialog, which ->
                        dialog.dismiss()
                        executor.execute()
                    }

                    setNegativeButton(R.string.cancel){ dialog, which ->
                        dialog.dismiss()
                        executor.cancel()
                    }
                    show()
                }
            }
            .onGranted{

            }
            .onDenied{
                if (AndPermission.hasAlwaysDeniedPermission(softReference.get(), it)){

                }
            }
            .start()
    }
}