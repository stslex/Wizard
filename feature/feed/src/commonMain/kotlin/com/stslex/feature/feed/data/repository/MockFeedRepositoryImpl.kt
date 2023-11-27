package com.stslex.feature.feed.data.repository

import com.stslex.core.core.Logger
import com.stslex.feature.feed.data.model.FeedDataModel
import com.stslex.feature.feed.data.model.FilmDataModel
import kotlinx.coroutines.delay

class MockFeedRepositoryImpl : FeedRepository {

    override suspend fun getFeed(
        page: Int,
        pageSize: Int
    ): FeedDataModel {
        Logger.debug("getFeed page: $page, pageSize: $pageSize")
        delay(1000)
        return FeedDataModel(
            films = Array(pageSize) { index ->
                val itemIndex = page.dec() * pageSize + index
                when (itemIndex % 3) {
                    0 -> filmLoki
                    1 -> filmInfiniteWar
                    else -> filmSuperRhino
                }.copy(id = itemIndex.toString())
            }.toList(),
            hasNextPage = true
        )
    }

    private val filmLoki = FilmDataModel(
        id = "1",
        title = "Локи",
        description = "Сразу же после кражи Тессеракта в фильме «Мстители: Финал» (2019), альтернативная версия Локи попадает в «Управление временны́ми изменениями» (УВИ), бюрократическую организацию, которая существует вне пространства и времени. Богу обмана предстоит ответить за свои преступления против времени и перед ним встаёт выбор: подвергнуться стиранию из реальности или помочь УВИ в борьбе с большей угрозой.",
        imageUrl = "http://pico.kartinka.shop/poster/item/big/72418.jpg",
        rate = "8.2",
        genres = listOf("Боевик", "Фантастика", "Фэнтези", "Приключения")
    )

    private val filmInfiniteWar = FilmDataModel(
        id = "2",
        title = "Мстители: Война бесконечности",
        description = "Пока Мстители и их союзники продолжают защищать мир от различных опасностей, с которыми не смог бы справиться один супергерой, новая угроза возникает из космоса: Танос. Межгалактический тиран преследует цель собрать все шесть Камней Бесконечности - артефакты невероятной силы, с помощью которых можно менять реальность по своему желанию. Всё, с чем Мстители сталкивались ранее, вело к этому моменту - судьба Земли никогда ещё не была столь неопределённой.",
        imageUrl = "http://pico.kartinka.shop/poster/item/big/34114.jpg",
        rate = "8.4",
        genres = listOf("Боевик", "Фантастика", "Фэнтези", "Приключения")
    )

    private val filmSuperRhino = FilmDataModel(
        id = "3",
        title = "Вольт",
        description = "Вольт — собака-полицейский, звезда телесериала, в котором он сражается с преступниками и спасает мир. Но когда камеры отключаются, Вольт не понимает, что происходит, и думает, что всё, что его окружает, настоящее. Когда его хозяйка Пенни похищают, Вольт отправляется в реальное путешествие, чтобы спасти её. На помощь ему приходят два необычных спутника — кот Мистер и хомяк Ролли.",
        imageUrl = "http://pico.kartinka.shop/poster/item/big/2570.jpg",
        rate = "6.8",
        genres = listOf("Комедия", "Фантастика", "Семейный", "Приключения", "Мультфильм")
    )
}