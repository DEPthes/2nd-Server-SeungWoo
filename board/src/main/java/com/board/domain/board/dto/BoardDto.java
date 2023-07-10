package com.board.domain.board.dto;

import com.board.domain.board.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

//    public static BoardDto toBoardDto(Board board){
//        BoardDto boardDto = new BoardDto();
//        boardDto.setId(board.getId());
//        boardDto.setBoardWriter(board.getBoardWriter());
//        boardDto.setBoardPass(board.getBoardPass());
//        boardDto.setBoardTitle(board.getBoardTitle());
//        boardDto.setBoardContents(board.getBoardContents());
//        boardDto.setBoardHits(board.getBoardHits());
//        boardDto.setBoardCreatedTime(board.getCreatedTime());
//        boardDto.setBoardUpdatedTime(board.getUpdatedTime());
//        return boardDto;
//    }

    public static BoardDto toBoardDto(Board board){
        return BoardDto.builder()
                .id(board.getId())
                .boardWriter(board.getBoardWriter())
                .boardPass(board.getBoardPass())
                .boardTitle(board.getBoardTitle())
                .boardContents(board.getBoardContents())
                .boardHits(board.getBoardHits())
                .boardCreatedTime(board.getCreatedTime())
                .boardUpdatedTime(board.getUpdatedTime())
                .build();
    }

}
