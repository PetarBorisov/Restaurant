package com.example.demo_project.service;

import com.example.demo_project.model.dto.ReservationAddDTO;
import com.example.demo_project.model.entity.ReservationEntity;

import java.util.List;

public interface ReservationService {


    boolean addReservation(ReservationAddDTO reservationAddDTO);

    List<ReservationEntity> getAll();


}
