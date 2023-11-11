package com.example.demo_project.service.impl;

import com.example.demo_project.model.dto.ReservationAddDTO;
import com.example.demo_project.model.entity.DinnerEntity;
import com.example.demo_project.model.entity.ReservationEntity;
import com.example.demo_project.repository.ReservationRepository;
import com.example.demo_project.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }



        @Override
        public void addReservation(ReservationAddDTO reservationAddDTO) {
            Optional<ReservationEntity> existingEntity = reservationRepository.findById(reservationAddDTO.getId());

            if (existingEntity.isPresent()) {
                ReservationEntity reservationEntity = existingEntity.get();

                reservationEntity.setId(reservationAddDTO.getId());
                reservationEntity.setName(reservationAddDTO.getName());
                reservationEntity.setEmail(reservationAddDTO.getEmail());
                reservationEntity.setPhoneNumber(reservationAddDTO.getPhoneNumber());
                reservationEntity.setDate(reservationAddDTO.getDate());
                reservationEntity.setTime(reservationAddDTO.getTime());
                reservationEntity.setNumberPersons(reservationAddDTO.getNumberPersons());

                reservationRepository.save(reservationEntity);
            } else {
                ReservationEntity newReservation = this.modelMapper.map(reservationAddDTO, ReservationEntity.class);
                reservationRepository.save(newReservation);
            }
        }


    }
