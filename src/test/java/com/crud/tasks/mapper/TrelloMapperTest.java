package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
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
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "Test_list_1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "Test_list_2", false);

        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertEquals(2, trelloLists.size());
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "Test_list_1", true);
        TrelloList trelloList2 = new TrelloList("2", "Test_list_2", false);
        TrelloList trelloList3 = new TrelloList("3", "Test_list_3", false);

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists.add(trelloList3);

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(3, trelloListDtos.size());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard(
                "Test_task","Test_description","top","test_id");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("Test_task", trelloCardDto.getName());
        assertEquals("Test_description", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("test_id", trelloCardDto.getListId());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test_task","Test_description","bottom","test_id");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("Test_task", trelloCard.getName());
        assertEquals("Test_description", trelloCard.getDescription());
        assertEquals("bottom", trelloCard.getPos());
        assertEquals("test_id", trelloCard.getListId());
    }
}