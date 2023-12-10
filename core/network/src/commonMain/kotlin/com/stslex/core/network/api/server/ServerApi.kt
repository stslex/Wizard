package com.stslex.core.network.api.server

import com.stslex.core.core.AppDispatcher
import com.stslex.core.network.api.base.BaseNetworkClient
import com.stslex.core.network.api.base.model.DefaultRequest

interface ServerApi

// TODO add auth, reconnect, error handling
class ServerApiImpl(
    dispatcher: AppDispatcher
) : ServerApi, BaseNetworkClient(
    appDispatcher = dispatcher,
    defaultRequest = DefaultRequest(
        hostUrl = "https://api.stslex.com",
        headers = mapOf(
            "Content-Type" to "application/json",
            "Accept" to "application/json"
        )
    )
)