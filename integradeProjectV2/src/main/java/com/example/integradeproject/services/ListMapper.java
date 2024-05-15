package com.example.integradeproject.services;

import com.example.integradeproject.dtos.NewTask2DTO;
import com.example.integradeproject.entities.Status;
import com.example.integradeproject.entities.Task;
import com.example.integradeproject.entities.Task2;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {
    private static final ListMapper listMapper = new ListMapper();
    private static ModelMapper modelMapper = new ModelMapper();

    private ListMapper() {
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass, ModelMapper modelMapper) {
        return source.stream().map(entity -> modelMapper.map(entity, targetClass)).collect(Collectors.toList());
    }



    public static ListMapper getInstance() {
        return listMapper;
    }

//        public static Task2 mapToTaskV2(NewTask2DTO newTask2DTO) {
//        Task2 task2 = new Task2();
//        task2.setTitle(newTask2DTO.getTitle());
//        task2.setDescription(newTask2DTO.getDescription());
//        task2.setAssignees(newTask2DTO.getAssignees());
//        task2.setStatusId(new Status(Integer.parseInt(String.valueOf(newTask2DTO.getStatusId())), "Status", null));
//        return task2;
//    }

}
