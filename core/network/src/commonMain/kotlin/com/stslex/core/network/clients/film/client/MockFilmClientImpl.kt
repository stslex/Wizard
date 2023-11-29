package com.stslex.core.network.clients.film.client

import com.stslex.core.core.Logger
import com.stslex.core.network.clients.film.model.FilmFeedResponse
import com.stslex.core.network.clients.film.model.FilmResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class MockFilmClientImpl : FilmClient {

    private val filmsFlow = MutableStateFlow(filmsList)

    override suspend fun getFeedFilms(
        page: Int,
        pageSize: Int
    ): FilmFeedResponse {
        Logger.debug("getFeed page: $page, pageSize: $pageSize")
        delay(1000)
        return FilmFeedResponse(
            results = Array(pageSize) { index ->
                val itemIndex = page.dec() * pageSize + index
                getFilmById(itemIndex)
            }.toList(),
            hasNext = true
        )
    }

    override fun getFilm(id: String): Flow<FilmResponse> = filmsFlow
        .map { filmsList ->
            filmsList[id.toInt() % filmsList.size].copy(id = id)
        }
        .filterNotNull()

    override suspend fun likeFilm(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun dislikeFilm(id: String) {
        TODO("Not yet implemented")
    }

    private fun getFilmById(id: Int) = filmsList[id % filmsList.size].copy(
        id = id.toString()
    )
}


private val lokiFilm = FilmResponse(
    id = "1",
    title = "Локи",
    description = "Сразу же после кражи Тессеракта в фильме «Мстители: Финал» (2019), альтернативная версия Локи попадает в «Управление временны́ми изменениями» (УВИ), бюрократическую организацию, которая существует вне пространства и времени. Богу обмана предстоит ответить за свои преступления против времени и перед ним встаёт выбор: подвергнуться стиранию из реальности или помочь УВИ в борьбе с большей угрозой.",
    poster = "http://pico.kartinka.shop/poster/item/big/72418.jpg",
    rating = "8.2",
    genres = listOf("Боевик", "Фантастика", "Фэнтези", "Приключения"),
    actors = listOf("Том Хиддлстон", "Софи Ди Мартин", "Оуэн Уилсон"),
    director = "Кейт Херон",
    year = "2021",
    duration = "50 мин.",
    country = "США",
    age = "16+",
    type = "Сериал",
    trailer = "https://www.youtube.com/watch?v=nW948Va-l10",
    isFavorite = false,
)

private val infiniteWar = FilmResponse(
    id = "2",
    title = "Мстители: Война бесконечности",
    description = "Пока Мстители и их союзники продолжают защищать мир от различных опасностей, с которыми не смог бы справиться один супергерой, новая угроза возникает из космоса: Танос. Межгалактический тиран преследует цель собрать все шесть Камней Бесконечности - артефакты невероятной силы, с помощью которых можно менять реальность по своему желанию. Всё, с чем Мстители сталкивались ранее, вело к этому моменту - судьба Земли никогда ещё не была столь неопределённой.",
    poster = "http://pico.kartinka.shop/poster/item/big/34114.jpg",
    rating = "8.4",
    genres = listOf("Боевик", "Фантастика", "Фэнтези", "Приключения"),
    actors = listOf("Роберт Дауни мл.", "Крис Хемсворт", "Марк Руффало"),
    director = "Энтони Руссо",
    year = "2018",
    duration = "149 мин.",
    country = "США",
    age = "12+",
    type = "Фильм",
    trailer = "https://www.youtube.com/watch?v=6ZfuNTqbHE8",
    isFavorite = true,
)

private val rhinoFilm = FilmResponse(
    id = "3",
    title = "Вольт",
    description = "Вольт — собака-полицейский, звезда телесериала, в котором он сражается с преступниками и спасает мир. Но когда камеры отключаются, Вольт не понимает, что происходит, и думает, что всё, что его окружает, настоящее. Когда его хозяйка Пенни похищают, Вольт отправляется в реальное путешествие, чтобы спасти её. На помощь ему приходят два необычных спутника — кот Мистер и хомяк Ролли.",
    poster = "http://pico.kartinka.shop/poster/item/big/2570.jpg",
    rating = "6.8",
    genres = listOf("Комедия", "Фантастика", "Семейный", "Приключения", "Мультфильм"),
    actors = listOf("Джон Траволта", "Майли Сайрус", "Сьюзи Эссман"),
    director = "Байрон Ховард",
    year = "2008",
    duration = "96 мин.",
    country = "США",
    age = "6+",
    type = "Фильм",
    trailer = "https://www.youtube.com/watch?v=6ZfuNTqbHE8",
    isFavorite = false,
)

private val filmsList = listOf(lokiFilm, infiniteWar, rhinoFilm)