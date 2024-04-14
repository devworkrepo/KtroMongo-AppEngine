package com.example.util

import io.ktor.server.application.*
import io.ktor.util.pipeline.*

typealias RouteContext = PipelineContext<Unit, ApplicationCall>

