package com.catnip.loginapp.utils.browser

import android.content.Context
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ProtectedMediaChromeClient(private val context: Context) : WebChromeClient() {
    override fun onPermissionRequest(request: PermissionRequest) {
        val resources = request.resources
        for (i in resources.indices) {
            if (PermissionRequest.RESOURCE_PROTECTED_MEDIA_ID == resources[i]) {
                request.grant(resources)
                return
            }
        }
        super.onPermissionRequest(request)
    }
}
