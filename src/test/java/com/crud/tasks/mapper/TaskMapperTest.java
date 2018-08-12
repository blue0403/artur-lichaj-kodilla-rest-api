package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void shouldMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"test_title","test_content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertNotNull(task);
        assertEquals("test_title", task.getTitle());
    }

    @Test
    public void shouldMapToTaskDto() {
        //Given
        Task task = new Task(1L,"test_title","test_content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertNotNull(taskDto);
        assertEquals("test_content", taskDto.getContent());
    }

    @Test
    public void shouldMapToTaskDtoList() {
        //Given
        Task task = new Task(1L,"test_title","test_content");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        //When
        List<TaskDto> taskDtoLists = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertNotNull(taskDtoLists);
        assertEquals(1, taskDtoLists.size());

        taskDtoLists.forEach(taskDtoList -> {
            assertEquals(1L, taskDtoList.getId().longValue());
            assertEquals("test_title",taskDtoList.getTitle());
            assertEquals("test_content",taskDtoList.getContent());
        });
    }
}