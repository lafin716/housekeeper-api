package com.lafin.housekeeper.service;

import com.lafin.housekeeper.dto.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 샘플 데이터 넣는 서비스
 * H2 DB 시작할 때 마다 초기화 되므로 이 서비스를 이용하여 샘플 데이터를 넣어준다
 */
@Service
@RequiredArgsConstructor
public class SampleService {

    private final MemberService memberService;

    private final HouseService houseService;

    private final RoomService roomService;

    private final ProductService productService;

    private final OrderMarketService orderMarketService;

    @PostConstruct
    public void initDatas() {
        var memberId = addMember();
        var houseId = addHouse(memberId, "기본집");
        var roomId1 = addRoom(houseId, "큰방");

        var product1 = addProduct(roomId1, "물티슈");
        var product2 = addProduct(roomId1, "스킨");
        var product3 = addProduct(roomId1, "로션");
        var product4 = addProduct(roomId1, "썬크림");

        addOrderMarket(product1, "쿠팡", "https://www.coupang.com/vp/products/227369485?itemId=720332517&vendorItemId=3091339209&sourceType=srp_product_ads&clickEventId=675b298c-7d6b-4931-a2d7-6d97194cd7bb&korePlacement=15&koreSubPlacement=1&q=%EB%AC%BC%ED%8B%B0%EC%8A%88&itemsCount=36&searchId=50dd1be109ef4385942c3ffdfb494880&rank=0&isAddedCart=");
        addOrderMarket(product2, "쿠팡", "https://www.coupang.com/vp/products/265626118?itemId=832679356&vendorItemId=5120021806&sourceType=srp_product_ads&clickEventId=d1420e23-bd9f-46e5-908c-734a7db41035&korePlacement=15&koreSubPlacement=1&q=%EC%97%AC%EC%9E%90+%EC%8A%A4%ED%82%A8%EB%A1%9C%EC%85%98&itemsCount=36&searchId=9edefd840a9344258c3d04d848ebabcb&rank=0&isAddedCart=");

        var roomId2 = addRoom(houseId, "작은방");
        addProduct(roomId2, "티슈");
        addProduct(roomId2, "모기약");
        addProduct(roomId2, "건전지");

        var roomId3 = addRoom(houseId, "부엌");
        addProduct(roomId3, "비닐봉지");
        addProduct(roomId3, "쓰레기봉투");
        addProduct(roomId3, "음식물쓰레기봉투");
        addProduct(roomId3, "소금");
        addProduct(roomId3, "후추");

        var roomId4 = addRoom(houseId, "화장실");
        addProduct(roomId4, "휴지");
        addProduct(roomId4, "칫솔");
        addProduct(roomId4, "치약");
        addProduct(roomId4, "클렌징폼");

    }

    // 회원 정보 세팅
    public Long addMember() {
        var memberJoinRequest = new MemberJoinRequest();
        memberJoinRequest.setEmail("lafin716@gmail.com");
        memberJoinRequest.setName("박재욱");
        memberJoinRequest.setPassword("1234");
        var member = memberService.join(memberJoinRequest);

        return member.getId();
    }

    // 집 정보 세팅
    public Long addHouse(Long memberId, String name) {
        var houseAddRequest = new HouseAddRequest();
        houseAddRequest.setMemberId(memberId);
        houseAddRequest.setName(name);
        var house = houseService.add(houseAddRequest);

        return house.getId();
    }

    // 방 정보 세팅
    public Long addRoom(Long houseId, String name) {
        var roomAddRequest = new RoomAddRequest();
        roomAddRequest.setHouseId(houseId);
        roomAddRequest.setName(name);
        var room = roomService.add(roomAddRequest);

        return room.getId();
    }

    // 물건 세팅
    public Long addProduct(Long roomId, String name) {
        var productAddRequest = new ProductAddRequest();
        productAddRequest.setRoomId(roomId);
        productAddRequest.setName(name);
        productAddRequest.setCount(35);
        productAddRequest.setMinimumCount(5);
        productAddRequest.setOrderCount(30);
        var product = productService.add(productAddRequest);

        return product.getId();
    }

    // 물건 마켓 세팅
    public Long addOrderMarket(Long productId, String name, String url) {
        var orderAddRequest = new OrderMarketAddRequest();
        orderAddRequest.setName(name);
        orderAddRequest.setUrl(url);

        var orderMarket = orderMarketService.addMarket(productId, orderAddRequest);
        return orderMarket.getId();
    }
}
