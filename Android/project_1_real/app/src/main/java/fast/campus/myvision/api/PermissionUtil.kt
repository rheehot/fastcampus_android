package fast.campus.myvision.api

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

class PermissionUtil {

    fun requestPermission(
        activity: Activity, requestCode : Int, vararg permissions : String
    ): Boolean {

        var granted = true
        val permissionNeeded = ArrayList<String>()

        permissions.forEach {
            val permissionCheck = ContextCompat.checkSelfPermission(activity, it)
            val hasPermission = permissionCheck == PackageManager.PERMISSION_GRANTED
            granted = granted and hasPermission //앞의 것 뒤에것 모두 true 될 때만 true 값

            if(!hasPermission){
                permissionNeeded.add(it)
            }
        }

        if(granted){
            return true
        } else {
            ActivityCompat.requestPermissions(
                activity, permissionNeeded.toTypedArray(), requestCode
            )
            return false
        }
    }
}
