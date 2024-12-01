package com.stslex.wizard.feature.auth.navigation

import com.stslex.wizard.core.ui.kit.mvi.Router
import com.stslex.wizard.feature.auth.ui.store.AuthStore

interface AuthRouter : Router<AuthStore.Action.Navigation>