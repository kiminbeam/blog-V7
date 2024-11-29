package com.example.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

@Import(BoardRepository.class)
@DataJpaTest // DB 관련된 자원들을 메모리(IoC)에 올린다.
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;


    @Test
    public void findById_test(){
        //given
        Integer id = 1;

        //when
        Optional<Board> boardOP = boardRepository.findById(id);
        Board board = boardOP.get();
        //eye
        System.out.println("LAZY loading 직전");
        String username = board.getUser().getUsername();
        System.out.println("LAZY loading 직후");
    }



    @Test
    public void findAll_test(){
        // given

        // when
        List<Board> boardList = boardRepository.findAll();
        System.out.println();

        // eye
        for(Board board : boardList){
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getCreatedAt());
            System.out.println("============");
        }

    }


}
