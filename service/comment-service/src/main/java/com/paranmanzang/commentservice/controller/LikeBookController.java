package com.paranmanzang.commentservice.controller;

import com.paranmanzang.commentservice.model.domain.LikeBookModel;
import com.paranmanzang.commentservice.service.impl.LikeBookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments/like-book")
@RequiredArgsConstructor
public class LikeBookController {
    private final LikeBookServiceImpl likeBookService;

    //좋아요
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody LikeBookModel likeBookModel) throws BindException {
        return ResponseEntity.ok(likeBookService.add(likeBookModel));
    }

    //좋아요 취소
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody LikeBookModel likeBookModel) throws BindException {
        return ResponseEntity.ok(likeBookService.remove(likeBookModel));
    }

    //좋아요 마이페이지 확인
    @GetMapping("/list/{nickname}")
    public ResponseEntity<?> findByNickname(@PathVariable String nickname){
        return ResponseEntity.ok(likeBookService.findAllByNickname(nickname));
    }
}
