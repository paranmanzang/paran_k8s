package com.paranmanzang.userservice.service;

import com.paranmanzang.userservice.model.domain.FriendModel;
import com.paranmanzang.userservice.model.entity.Friends;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FriendService {

    //친구 추가
    Object createFriend(FriendModel friendModel);

    //친구 삭제
    boolean deleteFriend(Long id);

    //친구 리스트 조회
    List<FriendModel> listFriends(String nickname) ;
}
