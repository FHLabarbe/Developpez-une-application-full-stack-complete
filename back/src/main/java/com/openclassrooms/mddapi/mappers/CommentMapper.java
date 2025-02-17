package com.openclassrooms.mddapi.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.entities.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    CommentDTO toDto(Comment entity);

    List<CommentDTO> toDtoList(List<Comment> entities);

}