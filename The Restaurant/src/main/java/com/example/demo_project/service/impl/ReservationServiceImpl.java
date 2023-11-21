package com.example.demo_project.service.impl;

import com.example.demo_project.model.dto.ReservationAddDTO;
import com.example.demo_project.model.entity.ReservationEntity;
import com.example.demo_project.repository.ReservationRepository;
import com.example.demo_project.service.ReservationService;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;
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
        public boolean addReservation(ReservationAddDTO reservationAddDTO) {
            Long id = reservationAddDTO.getId();
            if (id != null) {
                Optional<ReservationEntity> existingEntity = reservationRepository.findById(id);

                if (existingEntity.isPresent()) {
                    ReservationEntity reservationEntity = existingEntity.get();

                    reservationEntity.setName(reservationAddDTO.getName());
                    reservationEntity.setEmail(reservationAddDTO.getEmail());
                    reservationEntity.setPhoneNumber(reservationAddDTO.getPhoneNumber());
                    reservationEntity.setDate(reservationAddDTO.getDate());
                    reservationEntity.setTime(reservationAddDTO.getTime());
                    reservationEntity.setNumberPersons(reservationAddDTO.getNumberPersons());

                    reservationRepository.save(reservationEntity);
                    return false;
                }
            }

            ReservationEntity newReservation = this.modelMapper.map(reservationAddDTO, ReservationEntity.class);
            reservationRepository.save(newReservation);
            return false;
        }

    @Override
    public List<ReservationEntity> getAll() {
        return reservationRepository.findAll();
    }

    }


