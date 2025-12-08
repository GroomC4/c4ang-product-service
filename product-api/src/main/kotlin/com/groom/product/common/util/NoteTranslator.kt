package com.groom.product.common.util

/**
 * 향수 노트 이름 번역 유틸리티.
 *
 * 영어↔한국어 노트 이름 변환을 담당합니다.
 * 데이터는 영어로 저장하고, 필요시 한국어로 변환하여 제공합니다.
 */
object NoteTranslator {
    /**
     * 영어 → 한국어 번역 맵.
     */
    private val ENGLISH_TO_KOREAN: Map<String, String> =
        mapOf(
            // Citrus Smells
            "Bergamot" to "베르가못",
            "Bigarade" to "비가라드",
            "Bitter Orange" to "쓴오렌지",
            "Blood Orange" to "블러드 오렌지",
            "Lemon" to "레몬",
            "Lime" to "라임",
            "Orange" to "오렌지",
            "Grapefruit" to "자몽",
            "Mandarin Orange" to "만다린",
            "Tangerine" to "탠저린",
            "Yuzu" to "유자",
            "Neroli" to "네롤리",
            "Petitgrain" to "쁘띠그레인",
            "Citrus" to "시트러스",
            "Lemongrass" to "레몬그라스",
            // Fruits, Vegetables And Nuts
            "Apple" to "사과",
            "Apricot" to "살구",
            "Cherry" to "체리",
            "Peach" to "복숭아",
            "Pear" to "배",
            "Plum" to "자두",
            "Banana" to "바나나",
            "Blackberry" to "블랙베리",
            "Blueberry" to "블루베리",
            "Raspberry" to "라즈베리",
            "Strawberry" to "딸기",
            "Black Currant" to "블랙커런트",
            "Coconut" to "코코넛",
            "Almond" to "아몬드",
            "Walnut" to "호두",
            "Hazelnut" to "헤이즐넛",
            "Fig" to "무화과",
            "Grape" to "포도",
            "Watermelon" to "수박",
            "Melon" to "멜론",
            "Pineapple" to "파인애플",
            "Mango" to "망고",
            "Papaya" to "파파야",
            "Passion Fruit" to "패션프루트",
            "Kiwi" to "키위",
            "Pomegranate" to "석류",
            "Carrot" to "당근",
            "Tomato" to "토마토",
            "Lychee" to "리치",
            // Flowers
            "Rose" to "장미",
            "Jasmine" to "자스민",
            "Lavender" to "라벤더",
            "Lily" to "백합",
            "Peony" to "작약",
            "Gardenia" to "치자꽃",
            "Tuberose" to "튜베로즈",
            "Ylang-Ylang" to "일랑일랑",
            "Carnation" to "카네이션",
            "Violet" to "제비꽃",
            "Iris" to "아이리스",
            "Freesia" to "프리지아",
            "Magnolia" to "목련",
            "Lily of the Valley" to "은방울꽃",
            "Geranium" to "제라늄",
            "Narcissus" to "수선화",
            "Orange Blossom" to "오렌지 블라썸",
            "Lotus" to "연꽃",
            "Mimosa" to "미모사",
            "Honeysuckle" to "인동꽃",
            "Wisteria" to "등나무",
            "Hibiscus" to "히비스커스",
            "Chamomile" to "카모마일",
            "Marigold" to "메리골드",
            "Sunflower" to "해바라기",
            "Dahlia" to "달리아",
            "Orchid" to "난초",
            "Cherry Blossom" to "벚꽃",
            "Plumeria" to "플루메리아",
            "Lilac" to "라일락",
            "Hyacinth" to "히아신스",
            "Daffodil" to "수선화",
            "Chrysanthemum" to "국화",
            // Spices
            "Cinnamon" to "계피",
            "Cardamom" to "카다몬",
            "Clove" to "정향",
            "Nutmeg" to "육두구",
            "Black Pepper" to "후추",
            "Pink Pepper" to "핑크 페퍼",
            "Star Anise" to "팔각",
            "Ginger" to "생강",
            "Vanilla" to "바닐라",
            "Saffron" to "사프란",
            "Cumin" to "커민",
            "Coriander" to "고수",
            "Fennel" to "회향",
            "Anise" to "아니스",
            "Bay Leaf" to "월계수",
            "Allspice" to "올스파이스",
            "Turmeric" to "강황",
            "Paprika" to "파프리카",
            "Curry" to "커리",
            // Woods
            "Sandalwood" to "샌달우드",
            "Cedar" to "시더",
            "Pine" to "소나무",
            "Birch" to "자작나무",
            "Oak" to "참나무",
            "Bamboo" to "대나무",
            "Driftwood" to "유목",
            "Ebony" to "흑단",
            "Mahogany" to "마호가니",
            "Rosewood" to "로즈우드",
            "Teak" to "티크",
            "Cypress" to "사이프러스",
            "Juniper" to "주니퍼",
            "Fir" to "전나무",
            "Spruce" to "가문비나무",
            "Elm" to "느릅나무",
            "Ash" to "물푸레나무",
            "Maple" to "단풍나무",
            "Cherry Wood" to "체리우드",
            "Apple Wood" to "사과나무",
            "Olive Wood" to "올리브 나무",
            "Vetiver" to "베티버",
            "Patchouli" to "파츌리",
            "Oud" to "우드",
            // Resins
            "Amber" to "앰버",
            "Frankincense" to "프랑킨센스",
            "Myrrh" to "몰약",
            "Benzoin" to "벤조인",
            "Labdanum" to "라브다눔",
            "Opoponax" to "오포포낙스",
            "Elemi" to "엘레미",
            "Copal" to "코팔",
            "Dragon Blood" to "드래곤 블러드",
            "Styrax" to "스티락스",
            // Musks and Animal notes
            "Musk" to "머스크",
            "White Musk" to "화이트 머스크",
            "Ambergris" to "앰버그리스",
            "Civet" to "시벳",
            "Castoreum" to "카스토리움",
            "Ambroxan" to "암브록산",
            "Iso E Super" to "이소 E 슈퍼",
            // Green and Aromatic
            "Mint" to "민트",
            "Basil" to "바질",
            "Thyme" to "타임",
            "Rosemary" to "로즈마리",
            "Sage" to "세이지",
            "Oregano" to "오레가노",
            "Marjoram" to "마조람",
            "Eucalyptus" to "유칼립투스",
            "Tea Tree" to "티트리",
            "Pine Needles" to "솔잎",
            "Grass" to "풀",
            "Moss" to "이끼",
            "Fern" to "고사리",
            "Leaves" to "잎",
            "Green Notes" to "그린 노트",
            "Seaweed" to "해조류",
            "Algae" to "조류",
            // Gourmand and Sweet
            "Chocolate" to "초콜릿",
            "Coffee" to "커피",
            "Caramel" to "카라멜",
            "Honey" to "꿀",
            "Sugar" to "설탕",
            "Cream" to "크림",
            "Milk" to "우유",
            "Butter" to "버터",
            "Bread" to "빵",
            "Cookie" to "쿠키",
            "Cake" to "케이크",
            "Pie" to "파이",
            "Jam" to "잼",
            "Maple Syrup" to "메이플 시럽",
            "Marshmallow" to "마시멜로",
            "Cotton Candy" to "솜사탕",
            "Liquorice" to "감초",
            "Praline" to "프랄린",
            "Nougat" to "누가",
            "Toffee" to "토피",
            "Fudge" to "퍼지",
            // Alcoholic beverages
            "Wine" to "와인",
            "Champagne" to "샴페인",
            "Whiskey" to "위스키",
            "Rum" to "럼",
            "Brandy" to "브랜디",
            "Gin" to "진",
            "Vodka" to "보드카",
            "Beer" to "맥주",
            "Sake" to "사케",
            "Cognac" to "코냑",
            // Tea and Tobacco
            "Black Tea" to "홍차",
            "Green Tea" to "녹차",
            "White Tea" to "백차",
            "Oolong Tea" to "우롱차",
            "Earl Grey" to "얼그레이",
            "Jasmine Tea" to "자스민차",
            "Chai" to "차이",
            "Tobacco" to "담배",
            "Pipe Tobacco" to "파이프 담배",
            "Cigarette" to "담배",
            "Cuban Tobacco" to "쿠바 담배",
            // Marine and Aquatic
            "Sea Water" to "바닷물",
            "Ocean Breeze" to "바다 바람",
            "Salt" to "소금",
            "Seashells" to "조개껍질",
            "Coral" to "산호",
            "Kelp" to "다시마",
            "Plankton" to "플랑크톤",
            "Rain" to "비",
            "Water" to "물",
            "Ice" to "얼음",
            "Snow" to "눈",
            "Fog" to "안개",
            "Ozone" to "오존",
            // Leather and Animalic
            "Leather" to "가죽",
            "Suede" to "스웨이드",
            "Fur" to "모피",
            "Skin" to "피부",
            "Hair" to "머리카락",
            "Sweat" to "땀",
            "Body Odor" to "체취",
            // Metallic and Mineral
            "Metal" to "금속",
            "Iron" to "철",
            "Steel" to "강철",
            "Copper" to "구리",
            "Silver" to "은",
            "Gold" to "금",
            "Tin" to "주석",
            "Lead" to "납",
            "Stone" to "돌",
            "Flint" to "부싯돌",
            "Concrete" to "콘크리트",
            "Dust" to "먼지",
            "Sand" to "모래",
            "Clay" to "점토",
            "Chalk" to "분필",
            "Gunpowder" to "화약",
            "Sulfur" to "황",
            "Tar" to "타르",
            "Gasoline" to "휘발유",
            "Rubber" to "고무",
            "Plastic" to "플라스틱",
            // Additional common notes
            "Earthy" to "얼디",
            "Warm" to "웜",
            "Spicy" to "스파이시",
            "Aromatic" to "아로마틱",
            "Floral" to "플로랄",
            "Woody" to "우디",
            "Fruity" to "프루티",
            "Fresh" to "프레시",
            "Sweet" to "스위트",
            "Powdery" to "파우더리",
            "Oriental" to "오리엔탈",
            "Aquatic" to "아쿠아틱",
            "Citrusy" to "시트러시",
            "Musky" to "머스키",
            "Balsamic" to "발사믹",
            "Tonka Bean" to "통카빈",
            "Osmanthus" to "오스만투스",
            "Hedione" to "헤디온",
            "Coumarin" to "쿠마린",
            "Damask Rose" to "다마스크 로즈",
            "Bulgarian Rose" to "불가리안 로즈",
            "Tea" to "티",
        )

    /**
     * 한국어 → 영어 번역 맵.
     * ENGLISH_TO_KOREAN을 역으로 변환하고 추가 한국어 변형도 포함합니다.
     */
    private val KOREAN_TO_ENGLISH: Map<String, String> =
        buildMap {
            // 기본 역변환
            ENGLISH_TO_KOREAN.forEach { (eng, kor) ->
                put(kor, eng)
            }

            // 추가 한국어 변형 (동의어, 다른 표기법)
            // 꽃
            put("로즈", "Rose")
            put("피오니", "Peony")
            put("바이올렛", "Violet")
            put("매그놀리아", "Magnolia")
            put("오렌지블라썸", "Orange Blossom")

            // 스파이스
            put("핑크페퍼", "Pink Pepper")
            put("블랙페퍼", "Black Pepper")
            put("코리안더", "Coriander")

            // 우드
            put("시프레", "Cypress")
            put("팰리샌더", "Rosewood")
            put("산달우드", "Sandalwood")
            put("드라이우드", "Dry Wood")
            put("블론드우드", "Blonde Wood")

            // 레진/앰버
            put("랍다넘", "Labdanum")
            put("드래곤블러드", "Dragon Blood")

            // 머스크
            put("화이트머스크", "White Musk")
            put("이소이수퍼", "Iso E Super")

            // 구르망
            put("허니", "Honey")
            put("메이플시럽", "Maple Syrup")

            // 가죽
            put("레더", "Leather")

            // 차/음료
            put("자스민차", "Jasmine Tea")
            put("파이프담배", "Pipe Tobacco")
            put("쿠바담배", "Cuban Tobacco")

            // 그린/아로마틱
            put("그린노트", "Green Notes")

            // 기타 자주 쓰이는 표현
            put("페출리", "Patchouli")
            put("페출", "Patchouli")
            put("카시스", "Black Currant")
            put("다마스크", "Damask Rose")
            put("불가리안", "Bulgarian Rose")
            put("시프리올", "Cypriol")
            put("통카", "Tonka Bean")
            put("오리스", "Iris")
            put("머스", "Musk")
            put("우디", "Woody")
            put("노트", "Notes")
        }

    /**
     * 영어 노트 이름을 한국어로 변환합니다.
     *
     * @param englishName 영어 노트 이름
     * @return 한국어 노트 이름 (매핑이 없으면 원본 반환)
     */
    fun toKorean(englishName: String): String = ENGLISH_TO_KOREAN[englishName] ?: englishName

    /**
     * 한국어 노트 이름을 영어로 변환합니다.
     *
     * @param koreanName 한국어 노트 이름
     * @return 영어 노트 이름 (매핑이 없으면 원본 반환)
     */
    fun toEnglish(koreanName: String): String = KOREAN_TO_ENGLISH[koreanName] ?: koreanName

    /**
     * 영어 노트 이름 목록을 한국어로 변환합니다.
     *
     * @param englishNames 영어 노트 이름 목록
     * @return 한국어 노트 이름 목록
     */
    fun toKoreanList(englishNames: List<String>): List<String> = englishNames.map { toKorean(it) }

    /**
     * 한국어 노트 이름 목록을 영어로 변환합니다.
     *
     * @param koreanNames 한국어 노트 이름 목록
     * @return 영어 노트 이름 목록
     */
    fun toEnglishList(koreanNames: List<String>): List<String> = koreanNames.map { toEnglish(it) }

    /**
     * 지원하는 모든 영어 노트 이름을 반환합니다.
     */
    fun getAllEnglishNotes(): Set<String> = ENGLISH_TO_KOREAN.keys

    /**
     * 지원하는 모든 한국어 노트 이름을 반환합니다.
     */
    fun getAllKoreanNotes(): Set<String> = KOREAN_TO_ENGLISH.keys

    /**
     * 해당 영어 노트 이름이 지원되는지 확인합니다.
     */
    fun isEnglishNoteSupported(englishName: String): Boolean = ENGLISH_TO_KOREAN.containsKey(englishName)

    /**
     * 해당 한국어 노트 이름이 지원되는지 확인합니다.
     */
    fun isKoreanNoteSupported(koreanName: String): Boolean = KOREAN_TO_ENGLISH.containsKey(koreanName)
}
