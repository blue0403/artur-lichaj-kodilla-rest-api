package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(1L, "test_title", "test_content");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        when(repository.save(task)).thenReturn(taskList.get(0));

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        assertNotNull(savedTask);
        assertEquals("test_title", savedTask.getTitle());
    }

    @Test
    public void shouldFetchEmptyTaskList() {
        //Given
        when(repository.findAll()).thenReturn(new ArrayList<>());

        //When
        List<Task> taskList = dbService.getAllTasks();

        //Then
        assertNotNull(taskList);
        assertEquals(0, taskList.size());
    }

    @Test
    public void shouldFetchTaskWithId() {
        //Given
        Task task_1 = new Task(1L, "test_title", "test_content");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task_1);

        when(repository.findById(1L)).thenReturn(Optional.of(taskList.get(0)));

        //When
        Optional<Task> task = dbService.getTask(1L);

        //Then
        assertNotNull(task);
        assertEquals("test_title", task.get().getTitle());
    }

    @Test
    public void shouldDeleteTask() {
        //Given

        //When
        dbService.deleteTask(1L);

        //Then
        verify(repository, times(1)).delete(1L);
    }
}