package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "Test_list_1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "Test_list_2", false);

        List<TrelloListDto> trelloListDtos1 = new ArrayList<>();
        trelloListDtos1.add(trelloListDto1);
        trelloListDtos1.add(trelloListDto2);

        List<TrelloListDto> trelloListDtos2 = new ArrayList<>();
        trelloListDtos2.add(trelloListDto1);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("Test_board_1", "1", trelloListDtos1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("Test_board_2", "2", trelloListDtos2);

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto1);
        trelloBoardDtos.add(trelloBoardDto2);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertEquals(2, trelloBoards.size());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "Test_list_1", true);
        TrelloList trelloList2 = new TrelloList("2", "Test_list_2", false);

        List<TrelloList> trelloLists1 = new ArrayList<>();
        trelloLists1.add(trelloList1);
        trelloLists1.add(trelloList2);

        List<TrelloList> trelloLists2 = new ArrayList<>();
        trelloLists2.add(trelloList1);

        TrelloBoard trelloBoard1 = new TrelloBoard("1", "Test_board_1", trelloLists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "Test_board_2", trelloLists2);
        TrelloBoard trelloBoard3 = new TrelloBoard("3", "Test_board_3", trelloLists2);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        trelloBoards.add(trelloBoard3);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(3, trelloBoardDtos.size());
    }

    @Test
    public void shouldMapToList() {
        //Given
        //When
        //Then
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        //When
        //Then
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        //When
        //Then
    }

    @Test
    public void shouldMapToCard() {
        //Given
        //When
        //Then
    }
}