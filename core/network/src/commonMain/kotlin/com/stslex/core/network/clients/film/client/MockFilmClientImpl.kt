package com.stslex.core.network.clients.film.client

import com.stslex.core.core.Logger
import com.stslex.core.core.randomUuid
import com.stslex.core.network.clients.film.model.FilmItemNetwork
import com.stslex.core.network.clients.film.model.FilmListNetwork
import com.stslex.core.network.clients.film.model.FilmTrailerNetwork
import com.stslex.core.network.clients.film.model.MovieNetwork
import kotlinx.coroutines.delay
import kotlin.coroutines.coroutineContext

class MockFilmClientImpl : FilmClient {

    override suspend fun getFeedFilms(
        page: Int,
        pageSize: Int
    ): FilmListNetwork {
        Logger.debug("context: $coroutineContext")
        delay(1000)
        return FilmListNetwork(
            results = Array(pageSize) { index ->
                if (index >= filmsList.size) {
                    getFilmByIndex(index)
                } else {
                    filmsList[index]
                }
            }.toList(),
            hasNext = true
        )
    }

    override suspend fun getFilm(id: String): MovieNetwork = getMovieById(id)

    override suspend fun getFilmTrailers(id: String): List<FilmTrailerNetwork> = emptyList()

    override suspend fun likeFilm(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun dislikeFilm(id: String) {
        TODO("Not yet implemented")
    }

    private fun getFilmByIndex(index: Int) = filmsList[index % filmsList.size].copy(
        id = randomUuid()
    )

    private fun getMovieById(id: String) = movieList.firstOrNull { it.id == id }
        ?: throw IllegalArgumentException("Movie with id $id not found")
}

private val lokiMovieFilm = MovieNetwork(
    id = "b2cb4f71-1bf1-43fa-b047-4378f77d1286",
    title = "Локи",
    description = "Сразу же после кражи Тессеракта в фильме «Мстители: Финал» (2019), альтернативная версия Локи попадает в «Управление временны́ми изменениями» (УВИ), бюрократическую организацию, которая существует вне пространства и времени. Богу обмана предстоит ответить за свои преступления против времени и перед ним встаёт выбор: подвергнуться стиранию из реальности или помочь УВИ в борьбе с большей угрозой.",
    poster = "https://pico.kartinka.shop/poster/item/big/72418.jpg",
    rating = "8.2",
    genres = listOf("Боевик", "Фантастика", "Фэнтези", "Приключения"),
    year = "2021",
    duration = "50 мин.",
    countries = listOf("США"),
    age = "16+",
    type = "Сериал",
)

private val lokiFilm = lokiMovieFilm.toFilmItemNetwork()

private val infiniteWarMovie = MovieNetwork(
    id = "33fa9d73-a722-4efd-b95c-0dd6af25e6af",
    title = "Мстители: Война бесконечности",
    description = "Пока Мстители и их союзники продолжают защищать мир от различных опасностей, с которыми не смог бы справиться один супергерой, новая угроза возникает из космоса: Танос. Межгалактический тиран преследует цель собрать все шесть Камней Бесконечности - артефакты невероятной силы, с помощью которых можно менять реальность по своему желанию. Всё, с чем Мстители сталкивались ранее, вело к этому моменту - судьба Земли никогда ещё не была столь неопределённой.",
    poster = "https://pico.kartinka.shop/poster/item/big/34114.jpg",
    rating = "8.4",
    genres = listOf("Боевик", "Фантастика", "Фэнтези", "Приключения"),
//    actors = listOf("Роберт Дауни мл.", "Крис Хемсворт", "Марк Руффало"),
//    director = "Энтони Руссо",
    year = "2018",
    duration = "149 мин.",
    countries = listOf("США"),
    age = "12+",
    type = "Фильм",
)

private val infiniteWar = infiniteWarMovie.toFilmItemNetwork()

private val rhinoFilmMovie = MovieNetwork(
    id = "261fc394-e9cd-484e-bcd1-33152b7a0ba9",
    title = "Вольт",
    description = "Вольт — собака-полицейский, звезда телесериала, в котором он сражается с преступниками и спасает мир. Но когда камеры отключаются, Вольт не понимает, что происходит, и думает, что всё, что его окружает, настоящее. Когда его хозяйка Пенни похищают, Вольт отправляется в реальное путешествие, чтобы спасти её. На помощь ему приходят два необычных спутника — кот Мистер и хомяк Ролли.",
    poster = "https://pico.kartinka.shop/poster/item/big/2570.jpg",
    rating = "6.8",
    genres = listOf("Комедия", "Фантастика", "Семейный", "Приключения", "Мультфильм"),
//    actors = listOf("Джон Траволта", "Майли Сайрус", "Сьюзи Эссман"),
//    director = "Байрон Ховард",
    year = "2008",
    duration = "96 мин.",
    countries = listOf("США"),
    age = "6+",
    type = "Фильм",
//    trailer = "https://www.youtube.com/watch?v=6ZfuNTqbHE8",
)

private val rhinoFilm = rhinoFilmMovie.toFilmItemNetwork()

private val filmsList = listOf(lokiFilm, infiniteWar, rhinoFilm)
private val movieList = listOf(lokiMovieFilm, infiniteWarMovie, rhinoFilmMovie)

private fun MovieNetwork.toFilmItemNetwork() = FilmItemNetwork(
    id = id,
    title = title,
    poster = poster,
    rating = rating,
    genres = genres,
    year = year,
    type = type,
    countries = countries
)
