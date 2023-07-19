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
        int pageLimit = 3;
        Page<Board> boards = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        Page<BoardDto> boardDtos = boards.map(board -> new BoardDto(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
        return boardDtos;
    }
}
