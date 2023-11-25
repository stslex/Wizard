package com.stslex.feature.home.domain

interface HomeInteractor {

    val greet: String
}

class HomeInteractorImpl : HomeInteractor {

    override val greet: String
        get() = "hello world greet"
}