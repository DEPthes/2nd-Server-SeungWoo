package com.board.domain.board;

import com.board.domain.board.dto.BoardDto;
import com.board.domain.board.entity.BoardEntity;
import com.board.domain.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Repository.save() 같은 메소드는 매개변수와 반환값이 'Entity'임
// DTO -> Entity (Entity class)
// Entity -> DTO (DTO class)

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDto boardDto) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto);
        boardRepository.save(boardEntity);
    }

    public List<BoardDto> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityList){
            boardDtoList.add(BoardDto.toBoardDto(boardEntity));
        }
        return boardDtoList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);

    }

    public BoardDto findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDto boardDto = BoardDto.toBoardDto(boardEntity);
            return boardDto;
        }else
            return null;
    }

    public BoardDto update(BoardDto boardDto) {
        // spring data jpa는 update가 따로 없음 --> save로 insert, update 모두 함
        // 구별 방법 : id 값이 있느냐 없느냐 // id 값이 존재하면 update (db에 있는 것일테니까)
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDto);
        boardRepository.save(boardEntity);
        return findById(boardDto.getId()); // repository 통해서 find.. 해서 리턴해도 무방
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}