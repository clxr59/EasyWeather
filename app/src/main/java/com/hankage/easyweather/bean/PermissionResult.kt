package com.hankage.easyweather.bean

/**
 * Author: cheers
 * Time ： 2020-07-25
 * Description ：
 */

sealed class PermissionResult

object PermissionGranted : PermissionResult()

object PermissionDenied : PermissionResult()