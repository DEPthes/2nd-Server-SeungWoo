package com.board.domain.board.entity;

import com.board.domain.BaseEntity;
import com.board.domain.board.dto.BoardDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    public static Board toEntity(BoardDto boardDto){
        return Board.builder()
                .id(boardDto.getId())
                .boardWriter(boardDto.getBoardWriter())
                .boardPass(boardDto.getBoardPass())
                .boardTitle(boardDto.getBoardTitle())
                .boardContents(boardDto.getBoardContents())
                .boardHits(boardDto.getBoardHits())
                .build();
    }
}
