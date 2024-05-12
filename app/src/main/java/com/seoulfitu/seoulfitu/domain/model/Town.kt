package com.seoulfitu.seoulfitu.domain.model

enum class Town(val townName: String) {
    Gangnam("강남구"),
    Gangdong("강동구"),
    Gangbook("강북구"),
    Gangseo("강서구"),
    Gwanak("관악구"),
    Gwangjin("광진구"),
    Guro("구로구"),
    Gemchun("금천구"),
    Nowon("노원구"),
    Dobong("도봉구"),
    Dongdemun("동대문구"),
    Dongjak("동작구"),
    Mapo("마포구"),
    Seodemun("서대문구"),
    Seocho("서초구"),
    Sungdong("성동구"),
    Sungbook("성북구"),
    Songpa("송파구"),
    Yangcheon("양천구"),
    Yungdengpo("영등포구"),
    Yongsan("용산구"),
    Eungpyung("은평구"),
    Jongro("종로구"),
    Joong("중구"),
    Joongrang("중랑구"), ;

    companion object {
        fun findTownName(address: String): String {
            return entries.find { town ->
                address.contains(town.townName)
            }?.townName ?: Joong.townName
        }
    }
}
