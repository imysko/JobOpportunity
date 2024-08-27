package com.imysko.features.authorization.di

import com.imysko.data.authorization.AuthorizationRepository
import kotlin.properties.Delegates.notNull

interface AuthorizationDeps {

    val authorizationRepository: AuthorizationRepository
}

interface AuthorizationDepsProvider {

    val deps: AuthorizationDeps

    companion object : AuthorizationDepsProvider by AuthorizationDepsStore
}

object AuthorizationDepsStore : AuthorizationDepsProvider {

    override var deps: AuthorizationDeps by notNull()
}
