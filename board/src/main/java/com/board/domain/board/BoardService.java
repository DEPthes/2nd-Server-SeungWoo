package com.board.domain.board;

import com.board.domain.board.dto.BoardDto;
import com.board.domain.board.entity.Board;
import com.board.domain.board.repository.BoardRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final EntityManager entityManager; // jpql 사용 시 필요

    public void save(BoardDto boardDto) {
        Board board = Board.toEntity(boardDto);
        boardRepository.save(board);
    }

    public List<BoardDto> findAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(Board board : boardList){
            boardDtoList.add(BoardDto.toBoardDto(board));
        }
        return boardDtoList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);

    }

//    public BoardDto findById(Long id) {
//        Optional<Board> optionalBoardEntity = boardRepository.findById(id);
//        if(optionalBoardEntity.isPresent()){
//            Board board = optionalBoardEntity.get();
//            BoardDto boardDto = BoardDto.toBoardDto(board);
//            return boardDto;
//        }else
//            return null;
//    }

    public BoardDto findById(Long id) { // jpql
        Board board = entityManager
                .createQuery("SELECT b FROM Board b WHERE b.id = :id", Board.class)
                .setParameter("id", id)
                .getSingleResult();

        if (board != null) {
            BoardDto boardDto = BoardDto.toBoardDto(board);
            return boardDto;
        } else {
            return null;
        }
    }

    public BoardDto update(BoardDto boardDto) {
        Board board = Board.toEntity(boardDto);
        boardRepository.save(board);
        return findById(boardDto.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 몇 개?
        // 한 페이지 당 글 3개, id 기준으로 내림차순 정렬
        Page<Board> boards = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        System.out.println("boardEntities.getContent() = " + boards.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boards.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boards.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boards.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boards.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boards.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boards.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boards.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<BoardDto> boardDtos = boards.map(board -> new BoardDto(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
        return boardDtos;
    }
}
