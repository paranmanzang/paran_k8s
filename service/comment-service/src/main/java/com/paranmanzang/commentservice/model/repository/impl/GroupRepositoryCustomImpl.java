package com.paranmanzang.commentservice.model.repository.impl;

import com.paranmanzang.commentservice.model.domain.GroupResponseModel;
import com.paranmanzang.commentservice.model.repository.GroupRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.paranmanzang.commentservice.model.entity.QGroup.group;
import static com.paranmanzang.commentservice.model.entity.QJoining.joining;


@RequiredArgsConstructor
public class GroupRepositoryCustomImpl implements GroupRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<GroupResponseModel> findGroup(Pageable pageable) {
        var ids = queryFactory
                .select(group.id)
                .from(group)
                .where(group.enabled.eq(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(group.id.desc())
                .fetch();

        List<GroupResponseModel> books = ids.isEmpty() ? List.of() :
                queryFactory
                        .select(Projections.constructor(
                                GroupResponseModel.class,
                                group.id,
                                group.name,
                                group.categoryName,
                                group.createAt,
                                group.enabled,
                                group.detail,
                                group.nickname,
                                group.chatRoomId
                        ))
                        .from(group)
                        .where(group.id.in(ids))
                        .fetch();

        return new PageImpl<>(books, pageable, ids.size());
    }

    @Override
    public List<GroupResponseModel> findByNickname(String nickname) {
        var ids = queryFactory
                .select(group.id)
                .from(group)
                .join(group.joinings, joining)
                .where(joining.nickname.eq(nickname).and(joining.enabled.eq(true)))
                .orderBy(group.id.desc())
                .fetch();

        return ids.isEmpty() ? List.of() :
                queryFactory
                        .select(Projections.constructor(
                                GroupResponseModel.class,
                                group.id,
                                group.name,
                                group.categoryName,
                                group.createAt,
                                group.enabled,
                                group.detail,
                                group.nickname,
                                group.chatRoomId
                        ))
                        .from(group)
                        .where(group.id.in(ids))
                        .fetch();
    }

    @Override
    public Page<GroupResponseModel> findByEnable(Pageable pageable) {
        var ids = queryFactory
                .select(group.id)
                .from(group)
                .where(group.enabled.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(group.id.desc())
                .fetch();

        List<GroupResponseModel> books = ids.isEmpty() ? List.of() :
                queryFactory
                        .select(Projections.constructor(
                                GroupResponseModel.class,
                                group.id,
                                group.name,
                                group.categoryName,
                                group.createAt,
                                group.enabled,
                                group.detail,
                                group.nickname,
                                group.chatRoomId
                        ))
                        .from(group)
                        .where(group.id.in(ids))
                        .fetch();
        return new PageImpl<>(books, pageable, ids.size());
    }
}