package com.itsc.tuwoda

data class Office(
    val name: String? = null,
    val address: String,
    val distance: Int,
    val openHours: String? = null,
    val openHoursIndividual: String? = null,
    val salePointFormat: String? = null,
    val officeType: String? = null,
    val kep: Boolean? = null,
    val rko: String? = null,

)

val allOffices = listOf<Office>(
    Office(
        name = "ДО «Солнечногорский» Филиала № 7701 Банка ВТБ (ПАО)",
        address = "141506, Московская область, г. Солнечногорск, ул. Красная, д. 60",
        distance = 62105,
        openHours =
        "пн 09:00-18:00\n" +
                "вт 09:00-18:00\n" +
                "ср 09:00-18:00\n" +
                "чт 09:00-18:00\n" +
                "пт 09:00-17:00\n" +
                "сб выходной\n" +
                "вс выходной",
        openHoursIndividual =
        "пн 09:00-20:00\n" +
                "вт 09:00-20:00\n" +
                "ср 09:00-20:00\n" +
                "чт 09:00-20:00\n" +
                "пт 09:00-20:00\n" +
                "сб 10:00-17:00\n" +
                "вс выходной",
        salePointFormat = "Универсальный",
        officeType = "Да (Зона Привилегия)",
        kep = true,
        rko = "есть РКО"
    ),
    Office(
        name = "ДО «Останкино» Филиала № 7701 Банка ВТБ (ПАО)",
        address = "127427, г. Москва, Академика Королева, д. 12",
        distance = 7700,
        openHours =
        "Не обслуживает ЮЛ",
        openHoursIndividual =
        "пн 10:00-19:00\n" +
                "вт 10:00-19:00\n" +
                "ср 10:00-19:00\n" +
                "чт 10:00-19:00\n" +
                "пт 10:00-18:00\n" +
                "сб выходной\n" +
                "вс выходной",
        salePointFormat = "Микро (РБ)",
        officeType = "нет (пропускной режим)",
        kep = false,
        rko = "нет РКО"
    ),
    Office(
        name = "ДО «Басманный» Филиала в г. Москве",
        address = "107174, г. Москва, ул. Новая Басманная, д. 2/1, стр. 1",
        distance = 62105,
        openHours =
        "пн 09:00-18:00\n" +
                "вт 09:00-18:00\n" +
                "ср 09:00-18:00\n" +
                "чт 09:00-18:00\n" +
                "пт 09:00-17:00\n" +
                "сб выходной\n" +
                "вс выходной",
        openHoursIndividual =
        "Не обслуживает ФЛ",
        salePointFormat = "Корпоративный",
        officeType = "действует пропускная система, офис находится в здании ОАО РЖД",
        kep = false,
        rko = "нет РКО"
    )
)
val allAtms = listOf<Office>(
    Office(
        address = "ул. Богородский Вал, д. 6, корп. 1",
        distance = 62105
    ),
    Office(
        address = "ул. Скобелевская, д. 23",
        distance = 21965
    ),
    Office(
        address = "пр-кт Ленинский, д. 111, корп. 1",
        distance = 4181
    ),
    Office(
        address = "ул. Свободы, д. 13/2, стр. 1А",
        distance = 1857
    )
)