package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchTasksList() throws Exception {
        //Given
        Task task_1 = new Task(1L, "test_title", "test_content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task_1);

        TaskDto mappedTask_1 = new TaskDto(task_1.getId(), task_1.getTitle(), task_1.getContent());
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(mappedTask_1);

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test_title")))
                .andExpect(jsonPath("$[0].content", is("test_content")));
    }

    @Test
    public void shouldFetchTaskWithId() throws Exception {
        //Given
        Task task_1 = new Task(1L, "test", "test_content");
        TaskDto mappedTask_1 = new TaskDto(task_1.getId(), task_1.getTitle(), task_1.getContent());

        when(service.getTask(1L)).thenReturn(Optional.of(task_1));
        when(taskMapper.mapToTaskDto(task_1)).thenReturn(mappedTask_1);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.content", is("test_content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(4L, "Test", "Test_content");
        Task mappedTask = new Task(taskDto.getId(), taskDto.getTitle(), taskDto.getContent());

        when(taskMapper.mapToTask(Matchers.any(TaskDto.class))).thenReturn(mappedTask);
        when(service.saveTask(mappedTask)).thenReturn(mappedTask);

        //When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        Task task = new Task(6L, "To delete task", "Task_content");

        service.deleteTask(6L);

        //When & Then
        mockMvc.perform(delete("/v1/tasks/6")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto updatedTaskDto = new TaskDto(1L, "Test task", "Test Content");
        Task mappedTask = new Task(updatedTaskDto.getId(), updatedTaskDto.getTitle(), updatedTaskDto.getContent());

        TaskDto taskDtoBefore = new TaskDto(1L, "test_task", "test_content");

        when(taskMapper.mapToTask(Matchers.any(TaskDto.class))).thenReturn(mappedTask);
        when(service.saveTask(mappedTask)).thenReturn(mappedTask);
        when(taskMapper.mapToTaskDto(mappedTask)).thenReturn(updatedTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDtoBefore);

        //When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test task")))
                .andExpect(jsonPath("$.content", is("Test Content")));
    }
}