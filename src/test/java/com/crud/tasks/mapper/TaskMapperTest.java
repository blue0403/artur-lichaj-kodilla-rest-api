package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

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
        TaskDto taskDto =  taskMapper.mapToTaskDto(task);

        //Then
        assertNotNull(taskDto);
        assertEquals("test_content", taskDto.getContent());
    }
}