package com.stslex.core.network.client.base

import com.stslex.core.core.AppDispatcher
import com.stslex.core.network.client.base.BaseNetworkClient

class DefaultNetworkClientImpl(
    dispatcher: AppDispatcher
) : BaseNetworkClient(dispatcher)