package com.example.controller

import com.example.util.RoleRight
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseController : KoinComponent {

    val roleRight by inject<RoleRight>()


}